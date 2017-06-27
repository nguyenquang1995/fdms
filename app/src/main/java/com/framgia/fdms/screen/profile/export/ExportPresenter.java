package com.framgia.fdms.screen.profile.export;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.SaveFormat;
import com.aspose.words.Shape;
import com.framgia.fdms.R;
import com.framgia.fdms.data.model.Device;
import com.framgia.fdms.data.model.User;
import com.framgia.fdms.utils.Utils;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static com.framgia.fdms.utils.Constant.FOLDER_NAME_FAMS;

/**
 * Created by tuanbg on 6/14/17.
 */

public class ExportPresenter implements ExportContract.Presenter {
    private static final int NUMBER_COLUMN_TABLE = 4;
    private static final int FONT_SIZE = 5;
    private static final String FILE_NAME_SAVED_PDF = ".pdf";
    private static final String FILE_NAME_SAVED_DOCX = ".docx";
    private static final int VALUE_IMAGE = 100;
    private static final float sTextSize = 20.7f;
    private Image mImage;
    private Paragraph mParagraph;
    private User mUser;
    private CompositeSubscription mCompositeSubscription;
    private ExportContract.ViewModel mViewModel;
    private DocumentBuilder mBuilder;

    public ExportPresenter(User user, ExportContract.ViewModel viewModel) {
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

    private Object createPdf(List<Device> list) {
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
                generatePDFTable(list).setPaddingTop(VALUE_IMAGE);
                document.add(generatePDFTable(list));
                document.close();
                return file.getPath();
            } catch (DocumentException | IOException e) {
                return new NullPointerException(e.getCause().getMessage());
            }
        }
        return new NullPointerException(mViewModel.getString(R.string.title_user_not_found));
    }

    private void setHeaderReport() {
        mParagraph = new Paragraph();
        Paragraph paragraph = new Paragraph();
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(mViewModel.getString(R.string.title_devices_assignment_report));
        paragraph.setFont(FontFactory.getFont(FontFactory.COURIER, sTextSize));
        mParagraph.add(paragraph);
        mParagraph.add(new Paragraph(mViewModel.getString(R.string.title_full_name)
                + mUser.getFirstName()
                + " "
                + mUser.getLastName()));
        mParagraph.add(new Paragraph(mViewModel.getString(R.string.title_branch)));
        mParagraph.add(new Paragraph(mViewModel.getString(R.string.title_employcode))
                + mUser.getEmployeeCode());
    }

    private void getFileName() throws IOException, BadElementException {
        Drawable d = mViewModel.getDrawable(R.drawable.ic_logo_framgia);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, VALUE_IMAGE, stream);
        mImage = Image.getInstance(stream.toByteArray());
        mImage.scaleAbsolute(VALUE_IMAGE, VALUE_IMAGE);
    }

    private PdfPTable generatePDFTable(List<Device> devices) {
        PdfPTable table = new PdfPTable(NUMBER_COLUMN_TABLE);
        table.addCell(mViewModel.getString(R.string.title_device_name));
        table.addCell(mViewModel.getString(R.string.title_model_number));
        table.addCell(mViewModel.getString(R.string.title_serial_number));
        table.addCell(mViewModel.getString(R.string.title_assign_date));
        for (Device device : devices) {
            PdfPCell cellName = new PdfPCell(new Phrase(device.getProductionName()));
            table.addCell(cellName);

            PdfPCell cellModel = new PdfPCell(new Phrase(device.getModelNumber()));
            table.addCell(cellModel);

            PdfPCell cellSeri = new PdfPCell(new Phrase(device.getSerialNumber()));
            table.addCell(cellSeri);

            String boughtDate = Utils.getStringDate(device.getBoughtDate());
            PdfPCell cellAssign = new PdfPCell(new Phrase(boughtDate));
            table.addCell(cellAssign);
        }
        return table;
    }

    public String getCurentTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy", Locale.getDefault());
        return dateFormat.format(cal.getTime());
    }

    public void insertDocImage() throws Exception {
        if (mBuilder == null) return;
        Drawable d = mViewModel.getDrawable(R.drawable.ic_logo_framgia);
        BitmapDrawable bitDw = ((BitmapDrawable) d);
        Bitmap bmp = bitDw.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, VALUE_IMAGE, stream);
        Shape shape = mBuilder.insertImage(bmp);
        shape.setHeight(VALUE_IMAGE / 2);
        shape.setWidth(VALUE_IMAGE / 2);
    }

    public void insertDocHeaderReport() {
        if (mBuilder == null) return;
        mBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        mBuilder.getFont().setSize(FONT_SIZE);

        mBuilder.writeln(mViewModel.getString(R.string.title_devices_assignment_report));
        mBuilder.writeln();
        mBuilder.getParagraphFormat().clearFormatting();
        mBuilder.writeln(mViewModel.getString(R.string.title_full_name)
                + mUser.getFirstName()
                + " "
                + mUser.getLastName());
        mBuilder.writeln(mViewModel.getString(R.string.title_branch));
        mBuilder.writeln(
                mViewModel.getString(R.string.title_employcode) + mUser.getEmployeeCode());
    }

    public void insertDocTable(List<Device> devices) {
        if (mBuilder == null) return;
        mBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        mBuilder.getCellFormat().setWidth(VALUE_IMAGE / 2);
        mBuilder.insertCell();
        mBuilder.write(mViewModel.getString(R.string.title_device_name));
        mBuilder.insertCell();
        mBuilder.write(mViewModel.getString(R.string.title_model_number));
        mBuilder.insertCell();
        mBuilder.write(mViewModel.getString(R.string.title_serial_number));
        mBuilder.insertCell();
        mBuilder.write(mViewModel.getString(R.string.title_assign_date));
        mBuilder.endRow();
        mBuilder.getParagraphFormat().clearFormatting();

        for (Device device : devices) {
            mBuilder.insertCell();
            mBuilder.write(device.getProductionName());

            mBuilder.insertCell();
            mBuilder.write(device.getModelNumber());

            mBuilder.insertCell();
            mBuilder.write(device.getSerialNumber());

            mBuilder.insertCell();
            mBuilder.write(Utils.getStringDate(device.getBoughtDate()));
            mBuilder.endRow();
        }
        mBuilder.endTable();
    }

    private Object createDoc(List<Device> list) {
        if (mUser != null) {
            String fullName = mUser.getId() + "_" + mUser.getFirstName() + mUser.getLastName();
            File exportDir = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME_FAMS);
            if (!exportDir.exists()) exportDir.mkdirs();
            File file = new File(exportDir, fullName + "_" + getCurentTime() + FILE_NAME_SAVED_DOCX);
            OutputStream output;
            try {
                output = new FileOutputStream(file);
                com.aspose.words.Document document = new com.aspose.words.Document();
                mBuilder = new DocumentBuilder(document);
                insertDocImage();
                mBuilder.writeln();
                insertDocHeaderReport();
                mBuilder.writeln();
                insertDocTable(list);
                document.save(output, SaveFormat.DOCX);
                output.close();
                return file.getPath();
            } catch (Exception e) {
                return new NullPointerException(e.getCause().getMessage());
            }
        }
        return new NullPointerException(mViewModel.getString(R.string.title_user_not_found));
    }

    @Override
    public void exportDeviceByPdf(List<Device> list) {
        Observable.just(createPdf(list))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof String) {
                            String filePath = (String) o;
                            mViewModel.onExportPdfSuccess(filePath);
                        } else {
                            if (o instanceof NullPointerException) {
                                mViewModel.showMessage(R.string.message_export_error);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.showMessage(R.string.message_export_error);
                    }
                });
    }

    @Override
    public void exportDeviceByDoc(List<Device> list) {
        Observable.just(createDoc(list))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof String) {
                            String filePath = (String) o;
                            mViewModel.onExportDocSuccess(filePath);
                        } else {
                            if (o instanceof NullPointerException) {
                                mViewModel.showMessage(R.string.message_export_error);
                            }
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mViewModel.showMessage(R.string.message_export_error);
                    }
                });
    }

    @Override
    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
    }
}
