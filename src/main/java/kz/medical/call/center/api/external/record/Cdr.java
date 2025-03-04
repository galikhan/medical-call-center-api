package kz.medical.call.center.api.external.record;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

//@Introspected
@Serdeable
public record Cdr(LocalDateTime calldate,
                  String clid,
                  String src,
                  String dst,
                  String dcontext,
                  String channel,
                  String dstchannel,
                  String lastapp,
                  String lastdata,
                  Integer duration,
                  Integer billsec,
                  String disposition,
                  Integer amaflags,
                  String accountcode,
                  String uniqueid,
                  String userfield,
                  String did,
                  String recordingfile,
                  String cnum,
                  String cnam,
                  String outboundCnum,
                  String outboundCnam,
                  String dstCnam,
                  String linkedid,
                  String peeraccount,
                  Integer sequence) {


}
