package entities;
    public class Shifts {
        private String startTime;
        private String endTime;

        private double timeWorked;

        public Shifts(String startTime, String endTime , double timeWorked) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.timeWorked =  timeWorked;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public double getTimeWorked()
        {
            return  timeWorked;
        }
    }


