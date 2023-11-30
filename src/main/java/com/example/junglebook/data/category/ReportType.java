package com.example.junglebook.data.category;

public enum ReportType {
    AD("광고성 콘텐츠에요"),
    LACK("상품 정보가 부족해요"),
    FORBID("거래 금지 품목이에요"),
    FRAUD("안전 거래 거부 / 사기 의심"),
    ETC("기타");

    private final String description;

    ReportType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
