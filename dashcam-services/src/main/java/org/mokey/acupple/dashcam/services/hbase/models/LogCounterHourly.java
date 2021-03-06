package org.mokey.acupple.dashcam.services.hbase.models;

import org.apache.hadoop.hbase.util.Bytes;
import org.mokey.acupple.dashcam.common.utils.CamUtil;
import org.mokey.acupple.dashcam.hbase.annotations.Table;
import org.mokey.acupple.dashcam.services.utils.AggregateUtil;

/**
 * the count of logs per hour of all appId
 * Created by enousei on 3/11/16.
 */
@Table(name = "dashcam_app_hcounter")
public class LogCounterHourly extends CounterInfo{

    public LogCounterHourly(){}

    public LogCounterHourly(int appId, String envGroup, long time){
        this.appId = appId;
        this.envGroup = envGroup;
        this.time = time;
    }

    @Override
    public byte[] getRowKey() {
        byte[] envGroupHash = Bytes.toBytes(CamUtil.getHashCode(envGroup));
        return CamUtil.concat(envGroupHash, Bytes.toBytes(AggregateUtil.getHourPart(time)));
    }
}
