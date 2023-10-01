package net.neology.tolling.dev;

/**
 *
 */
public class PrivacyCords {

    /**
     *
     */
    public enum Version {MALFORMED,V1,V2};

    //V2 set of cords to also obscure the rest of the image that does not include the vehicle of interest
    private int outXone = -1;
    private int outYone = -1;
    private int outXtwo = -1;
    private int outYtwo = -1;
    //Original set of cords to obscure windscreen of a vehicle
    private int windXone = -1;
    private int windYone = -1;
    private int windXtwo = -1;
    private int windYtwo = -1;

    private Version myVersion = Version.MALFORMED;

    /**
     *
     * @param windXone
     * @param windYone
     * @param windXtwo
     * @param windYtwo
     */
    @Deprecated
    public PrivacyCords(int windXone, int windYone, int windXtwo, int windYtwo) {
        this.windXone = windXone;
        this.windYone = windYone;
        this.windXtwo = windXtwo;
        this.windYtwo = windYtwo;
        this.myVersion = validateVersion();
    }

    /**
     *
     * @param outXone
     * @param outYone
     * @param outXtwo
     * @param outYtwo
     * @param windXone
     * @param windYone
     * @param windXtwo
     * @param windYtwo
     */
    public PrivacyCords(int outXone, int outYone, int outXtwo, int outYtwo, int windXone, int windYone, int windXtwo, int windYtwo) {
        this.outXone = outXone;
        this.outYone = outYone;
        this.outXtwo = outXtwo;
        this.outYtwo = outYtwo;
        this.windXone = windXone;
        this.windYone = windYone;
        this.windXtwo = windXtwo;
        this.windYtwo = windYtwo;
        this.myVersion = validateVersion();
    }

    private Version validateVersion() {
        Version v = Version.MALFORMED;
        try {
            if(
                    (windXone >= 0 && windXtwo >= 0 && windYone >= 0 && windYtwo >=0)
                    && windYone <= windYtwo
                    && windXone <= windXtwo
            )
                v = Version.V1;
            if (v == Version.V1) {
                if (
                        (outXone >= 0 && outXtwo >=0 && outYone >= 0 && outYtwo >=0)
                        && outXone <= outXtwo
                        && outYone <= outYtwo
                        //TODO:ak:add check to ensure outside is > than windscreen inside...assume for now that this will be used properly
                )
                    v = Version.V2;
            }
        }
        catch (NullPointerException e) {
            //TODO:ak:do better...
            System.out.println("NPE ERROR:SOMETHINGWENTWRONG!");
        }

        return v;
    }

    public int getOutXone() {
        return outXone;
    }

    public void setOutXone(int outXone) {
        this.outXone = outXone;
    }

    public int getOutYone() {
        return outYone;
    }

    public void setOutYone(int outYone) {
        this.outYone = outYone;
    }

    public int getOutXtwo() {
        return outXtwo;
    }

    public void setOutXtwo(int outXtwo) {
        this.outXtwo = outXtwo;
    }

    public int getOutYtwo() {
        return outYtwo;
    }

    public void setOutYtwo(int outYtwo) {
        this.outYtwo = outYtwo;
    }

    public int getWindXone() {
        return windXone;
    }

    public void setWindXone(int windXone) {
        this.windXone = windXone;
    }

    public int getWindYone() {
        return windYone;
    }

    public void setWindYone(int windYone) {
        this.windYone = windYone;
    }

    public int getWindXtwo() {
        return windXtwo;
    }

    public void setWindXtwo(int windXtwo) {
        this.windXtwo = windXtwo;
    }

    public int getWindYtwo() {
        return windYtwo;
    }

    public void setWindYtwo(int windYtwo) {
        this.windYtwo = windYtwo;
    }

    public Version getMyVersion() {
        validateVersion();
        return myVersion;
    }

    // If you wanna do this...I guess go ahead at your own risk?
    public void setMyVersion(Version myVersion) {
        this.myVersion = myVersion;
    }
}
