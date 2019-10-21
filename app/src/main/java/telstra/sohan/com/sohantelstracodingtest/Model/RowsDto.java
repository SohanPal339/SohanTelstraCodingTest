package telstra.sohan.com.sohantelstracodingtest.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class RowsDto {

    @SerializedName("rows")
    private List<RowsDataDto> listRowsDto;

    @SerializedName("title")
    private String title;

    public List<RowsDataDto> getListRowsDto() {
        return listRowsDto;
    }

    public void setListRowsDto(List<RowsDataDto> listRowsDto) {
        this.listRowsDto = listRowsDto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

