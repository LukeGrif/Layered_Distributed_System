package validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("positivePaymentValidator")
public class PositivePaymentValidator implements Validator<Double> {

    @Override
    public void validate(FacesContext context, UIComponent component, Double value)
            throws ValidatorException {

        if (value == null || value <= 0) {
            FacesMessage msg = new FacesMessage(
                FacesMessage.SEVERITY_ERROR,
                "Payment must be greater than zero",
                "Please enter a positive payment amount");
            throw new ValidatorException(msg);
        }
    }
}
