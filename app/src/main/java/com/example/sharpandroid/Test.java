package com.example.sharpandroid;

/**
 * Created by jerry on 16/5/5.
 */
public class Test {
    public  KdLocation BDLocationToKDLocation(BdLocation bdLocation) {
        KdLocation kdLocation = new KdLocation();
        kdLocation.setLatitude(bdLocation.getLatitude());
        kdLocation.setLongitude(bdLocation.getLongitude());
        return kdLocation;
    }

    class KdLocation{
        private  Double latitude;
        private  Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }

    class BdLocation{
        private  Double latitude;
        private  Double longitude;

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
