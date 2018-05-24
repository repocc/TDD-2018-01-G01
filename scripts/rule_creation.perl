#!/usr/bin/perl
use strict;
use warnings;
use Data::Dumper;
use JSON qw( decode_json );
use LWP::UserAgent;

my $definition = "define-counter";
my $name = "spam-fraction";
my $operation =
(define-signal {"spam-fraction" (/ (counter-value "spam-count" [])
                                                (counter-value "email-count" []))}
               true)
             (define-counter "spam-important-table" [(current "spam")
                                                     (current "important")]
               true)

my $name   = $ARGV[0];
my $number = $ARGV[1];

my @dataArray = ($data0, $data1, $data2, $data3, $data4);

for(my $i = 0; $i < 100000; $i++) {
	sendRequest($dataArray [$i % 5]);
	sleep(2);
}


sub sendRequest {

	my($data) = @_;

	my $ua = LWP::UserAgent->new;

	my $server_endpoint = "http://localhost:8080/add-rules";

	# set custom HTTP request header fields
	my $req = HTTP::Request->new(POST => $server_endpoint);
	$req->header('content-type' => 'application/json');

	# add POST data to HTTP request body
	my $post_data = $data;
	$req->content($post_data);

	my $resp = $ua->request($req);

	if ($resp->is_success) {
	    my $message = $resp->decoded_content;
	    print "Received reply: $message\n";
	}
	else {
	    print "HTTP POST error code: ", $resp->code, "\n";
	    print "HTTP POST error message: ", $resp->message, "\n";
	}

}
