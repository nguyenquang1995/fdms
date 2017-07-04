unless ARGV[0].empty?
	require "chatwork"

	# Create message
	ChatWork.api_key = "8f247d4a711fb2326012a8ad47243db3"
	ChatWork::Message.create(room_id: 68078155, body: "[info][To:1729399] [To:2204808] [To:2357769]  \n\n CI Build Success! \n\n Check em di (yeu) :x! \n Link: #{ARGV[0]} [/info]")
end