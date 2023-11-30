package com.example.junglebook.data.category;

<<<<<<< Updated upstream
public enum ReportType {
=======
import lombok.Getter;

@Getter
public enum ReportType {

>>>>>>> Stashed changes
    AD("광고성 콘텐츠에요"),
    LACK("상품 정보가 부족해요"),
    FORBID("거래 금지 품목이에요"),
    FRAUD("안전 거래 거부 / 사기 의심"),
    ETC("기타");

    private final String description;

    ReportType(String description) {
        this.description = description;
    }

<<<<<<< Updated upstream
    public String getDescription() {
        return description;
    }
=======
>>>>>>> Stashed changes
}
