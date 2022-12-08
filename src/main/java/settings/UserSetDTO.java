package settings;

import java.util.List;

    public class UserSetDTO {
        private Long chatId;
        private String numberAfterComa;
        private String notifTime;
        private String selectBank;
        private String textInfo;
        private List<String> selectCurrency;

        public Long getChatId() {
            return chatId;
        }

        public void setChatId(Long chatId) {
            this.chatId = chatId;
        }

        public String getNumberAfterComa() {
            return numberAfterComa;
        }

        public void setNumberAfterComa(String numberAfterComa) {
            this.numberAfterComa = numberAfterComa;
        }

        public String getNotifTime() {
            return notifTime;
        }

        public void setNotifTime(String notifTime) {
            this.notifTime = notifTime;
        }

        public String getSelectBank() {
            return selectBank;
        }

        public void setSelectBank(String selectBank) {
            this.selectBank = selectBank;
        }

        public String getTextInfo() {
            return textInfo;
        }

        public void setTextInfo(String textInfo) {
            this.textInfo = textInfo;
        }

        public List<String> getSelectCurr() {
            return selectCurrency;
        }

        public void setSelectCurr(List<String> selectCurr) {
            this.selectCurrency = selectCurr;
        }
    }
