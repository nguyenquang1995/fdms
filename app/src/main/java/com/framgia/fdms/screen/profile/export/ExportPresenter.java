package com.framgia.fdms.screen.profile.export;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.widget.Toast;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Device;
import com.framgia.fdms.data.model.User;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.framgia.fdms.utils.Constant.FOLDER_NAME_FAMS;
import static com.framgia.fdms.utils.Constant.KeyExport.TITLE_ASSIGNED;
import static com.framgia.fdms.utils.Constant.KeyExport.TITLE_DEVICE_NAME;
import static com.framgia.fdms.utils.Constant.KeyExport.TITLE_MODEL_NUMBER;
import static com.framgia.fdms.utils.Constant.KeyExport.TITLE_SERIES_NUMBER;

/**
 * Created by tuanbg on 6/14/17.
 */

public class ExportPresenter implements ExportContract.Presenter {
    private static final int NUMBER_COLUMN_TABLE = 4;
    private static final String FILE_NAME_SAVED_PDF = ".pdf";
    private static final int VALUE_IMAGE = 100;
    private static final float sTextSize = 20.7f;
    private Image mImage;
    private Context mContext;
    private Paragraph mParagraph;
    private List<Device> mList = new ArrayList<>(); // get List device
    private User mUser;
    private CompositeSubscription mCompositeSubscription;
    private ExportContract.ViewModel mViewModel;

    public ExportPresenter(Context context, User user, ExportContract.ViewModel viewModel) {
        mContext = context;
        mUser = user;
        mCompositeSubscription = new CompositeSubscription();
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    private Object createPdf() {
        if (mUser != null) {
            String fullName = mUser.getId() + "_" + mUser.getFirstName() + mUser.getLastName();
            File exportDir = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME_FAMS);
            if (!exportDir.exists()) exportDir.mkdirs();
            File file = new File(exportDir, fullName + "_" + getCurentTime() + FILE_NAME_SAVED_PDF);
            OutputStream output;
            try {
                output = new FileOutputStream(file);
                Document document = new Document();
                PdfWriter.getInstance(document, output);
                document.open();
                getFileName();
                setHeaderReport();
                document.add(mImage);
                document.add(mParagraph);
                generatePDFTable().setPaddingTop(VALUE_IMAGE);
                document.add(generatePDFTable());
                document.close();
                return file.getPath();
            } catch (DocumentException | IOException e) {
                return new NullPointerException(e.getCause().getMessage());
            }
        }
        return new NullPointerException(mContext.getString(R.string.title_user_not_found));
    }

    private void setHeaderReport() {
        mParagraph = new Paragraph();
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(mContext.getString(R.string.title_devices_assignment_report));
        paragraph.setFont(FontFactory.getFont(FontFactory.COURIER, sTextSize));
        mParagraph.add(paragraph);
        mParagraph.add(new Paragraph(mContext.getString(R.string.title_full_name)
                + mUser.getFirstName()
                + " "
                + mUser.getLastName()));
        mParagraph.add(new Paragraph(mContext.getString(R.string.title_branch)));
        mParagraph.add(new Paragraph(mContext.getString(R.string.title_employcode))
                + mUser.getEmployeeCode());
    }

    private void getFileName() throws IOException, BadElementException {
        Drawable d = mContext.getResources().getDrawable(R.drawable.ic_logo_framgia);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, VALUE_IMAGE, stream);
        mImage = Image.getInstance(stream.toByteArray());
        mImage.scaleAbsolute(VALUE_IMAGE, VALUE_IMAGE);
    }

    private PdfPTable generatePDFTable() {
        // 4 columns table
        PdfPTable table = new PdfPTable(NUMBER_COLUMN_TABLE);
        table.addCell(TITLE_DEVICE_NAME);
        table.addCell(TITLE_MODEL_NUMBER);
        table.addCell(TITLE_SERIES_NUMBER);
        table.addCell(TITLE_ASSIGNED);
        for (int i = 0; i < mList.size(); i++) {
            table.addCell(mList.get(i) + "");
            table.addCell(String.valueOf(mList.get(i) + ""));
        }
        return table;
    }

    public String getCurentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        return dateFormat.format(cal.getTime());
    }

    public void exportTask() {
        Observable.just(createPdf())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof String) {
                            String filePath = (String) o;
                            String mess = filePath.length() > 0 ? mContext.getString(
                                    (R.string.message_export_success)) + filePath
                                    : mContext.getString(R.string.message_export_error);
                            Toast.makeText(mContext, mess, Toast.LENGTH_LONG).show();
                            mViewModel.openFilePDF(filePath);
                        } else {
                            String error = o instanceof NullPointerException
                                    ? ((NullPointerException) o).getCause().getMessage() : null;
                            Toast.makeText(mContext,
                                    mContext.getString(R.string.message_export_error) + error,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(mContext, mContext.getString(R.string.message_export_error),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
    }
}
