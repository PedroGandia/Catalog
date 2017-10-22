package android.test.catalog.data.local.models;

public class DataResponse {

    /**
     * Created by pedrog295@gmail.com on 21/10/2017.
     */

    private String kind;
    private Data data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Children {
        private String kind;
        private Data data;

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

}
