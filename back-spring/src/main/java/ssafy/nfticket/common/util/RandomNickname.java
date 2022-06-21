package ssafy.nfticket.common.util;

public class RandomNickname {
    String[] randomAdjective = {
        "즐거운", "기쁜", "슬픈", "짜릿한", "달콤한", "상큼한", "귀여운", "잘생긴", "예쁜",
            "시원한", "따뜻한", "뜨거운", "두꺼운", "새하얀", "새까만", "샛노란", "새빨간", "새파란",
            "푸릇한", "풋풋한", "똑똑한", "매콤한", "지독한", "눅눅한", "건조한", "빛나는", "달리는",
            "엄숙한", "정중한", "설레는", "새콤한", "차가운"
    };

    String[] randomNoun = {
        "사자", "늑대", "곰", "낙타", "표범", "원숭이", "사슴", "얼룩말", "호랑이", "캥거루",
            "코알라", "여우", "판다", "돼지", "소", "말", "당나귀", "염소", "양", "수탉", "암탉",
            "고양이", "개", "금붕어", "토끼", "햄스터", "앵무새", "광어", "연어", "송어", "전어",
            "고등어", "망둥어", "고래", "상어", "철갑상어", "방어", "빙어", "농어", "떡볶이", "갈비탕",
            "감자탕", "감자떡", "고슴도치", "개복치", "버스", "지하철", "기차", "택시"
    };

    public String makeRandomNickname() {
        String adjective = randomAdjective[(int) (Math.random() * randomAdjective.length)];
        String noun = randomNoun[(int) (Math.random() * randomNoun.length)];
        return adjective + " " + noun;
    }

}
