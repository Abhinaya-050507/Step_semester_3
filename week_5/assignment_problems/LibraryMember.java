package week_5.assignment_problems;

public class LibraryMember {

    static class Library {

        private String membershipId;
        private String name;
        private boolean premiumMember;
        private String securityAnswer;

        public Library() {
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String id) {

            if (membershipId == null)
                membershipId = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPremiumMember() {
            return premiumMember;
        }

        public void setPremiumMember(boolean premium) {
            premiumMember = premium;
        }

        public void setSecurityAnswer(String answer) {
            securityAnswer = Integer.toHexString(answer.hashCode());
        }
    }

    public static void main(String[] args) {

        Library m = new Library();

        m.setMembershipId("M101");
        m.setMembershipId("M202");

        m.setName("Rahul");
        m.setPremiumMember(true);

        m.setSecurityAnswer("hello");

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremiumMember());
    }
}