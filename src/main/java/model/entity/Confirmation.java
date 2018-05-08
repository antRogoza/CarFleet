package model.entity;

import model.entity.proxy.ConfirmationProxy;

public class Confirmation implements Entity<Long> {
    private Long id;
    private String emailConfirmed;
    private Long idUser;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(String emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public static final class ConfirmationBuilder{
        private Long id;
        private String emailConfirmed;
        private Long idUser;

        public ConfirmationBuilder setId(Long id){
            this.id = id;
            return this;
        }

        public ConfirmationBuilder setEmailConfirmed(String emailConfirmed){
            this.emailConfirmed = emailConfirmed;
            return this;
        }

        public ConfirmationBuilder setIdUser(Long idUser){
            this.idUser = idUser;
            return this;
        }

        public Confirmation buildConfirmation() {
            Confirmation confirmation = new Confirmation();
            confirmation.setId(id);
            confirmation.setEmailConfirmed(emailConfirmed);
            confirmation.setIdUser(idUser);
            return confirmation;
        }

        public Confirmation buildConfirmationProxy() {
            ConfirmationProxy confirmation = new ConfirmationProxy();
            confirmation.setId(id);
            confirmation.setEmailConfirmed(emailConfirmed);
            confirmation.setIdUser(idUser);
            return confirmation;
        }
    }

}
