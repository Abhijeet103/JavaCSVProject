package entities;
    public class Shifts {
        private String startTime;
        private String endTime;

        public Shifts(String startTime, String endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }
    }


