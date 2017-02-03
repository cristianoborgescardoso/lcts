package projects.localizationVANT.nodes.messages;

import projects.localizationVANT.localization.LPoint;
import projects.localizationVANT.nodes.nodeImplementations.MessageType;
import sinalgo.nodes.messages.Message;

public class BroadcastPositionMessage extends Message
{

    private LPoint posCalc;
    private LPoint posPerf;
    private double residual;
    private int src_id;
    private MessageType messageType;

    // sync
    private double timestamp;  //   When the packet was sent by the reference node
    private double delay; //   Estimated delay of the packet from sender to receiver
    private double hopsToBeacons; //   Estimated number of hops to beacons
    private double residualSync; //   How trusted is the synchronization info (residual value)
    private boolean senderIsBeacon;

    public LPoint getPosCalc()
    {
        //System.out.println("getPosCalc em message");
        //System.out.printf(" x = %f, y = %f\n", posCalc.getPosition().xCoord, posCalc.getPosition().yCoord);
        return posCalc;
    }

    public BroadcastPositionMessage(MessageType messageType)
    {
        this.messageType = messageType;
    }

    public BroadcastPositionMessage()
    {

    }

    public void setPosCalc(LPoint posCalc)
    {
        this.posCalc = posCalc;
    }

    public LPoint getPosPerf()
    {
        return posPerf;
    }

    public void setPosPerf(LPoint posPerf)
    {
        this.posPerf = posPerf;
    }

    public double getResidual()
    {
        return residual;
    }

    public void setResidual(double residual)
    {
        this.residual = residual;
    }

    public int getSrc_id()
    {
        return src_id;
    }

    public void setSrc_id(int src_id)
    {
        this.src_id = src_id;
    }

    public double getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(double timestamp)
    {
        this.timestamp = timestamp;
    }

    public double getDelay()
    {
        return delay;
    }

    public void setDelay(double delay)
    {
        this.delay = delay;
    }

    public double getHopsToBeacons()
    {
        return hopsToBeacons;
    }

    public void setHopsToBeacons(double hopsToBeacons)
    {
        this.hopsToBeacons = hopsToBeacons;
    }

    public double getResidualSync()
    {
        return residualSync;
    }

    public void setResidualSync(double residualSync)
    {
        this.residualSync = residualSync;
    }

    public boolean getSenderIsBeacon()
    {
        return senderIsBeacon;
    }

    public void setSenderIsBeacon(boolean senderIsBeacon)
    {
        this.senderIsBeacon = senderIsBeacon;
    }

    @Override
    public Message clone()
    {
        // TODO Auto-generated method stub
        return this;
    }

    public MessageType getMessageType()
    {
        return messageType;
    }

    public boolean isSenderIsBeacon()
    {
        return senderIsBeacon;
    }

}
