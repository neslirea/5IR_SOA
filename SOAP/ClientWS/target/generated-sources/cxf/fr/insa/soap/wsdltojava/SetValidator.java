
package fr.insa.soap.wsdltojava;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Classe Java pour setValidator complex type.
 * 
 * &lt;p&gt;Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="setValidator"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *         &amp;lt;element name="validator" type="{http://www.w3.org/2001/XMLSchema}int"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setValidator", propOrder = {
    "user",
    "validator"
})
public class SetValidator {

    protected int user;
    protected int validator;

    /**
     * Obtient la valeur de la propriété user.
     * 
     */
    public int getUser() {
        return user;
    }

    /**
     * Définit la valeur de la propriété user.
     * 
     */
    public void setUser(int value) {
        this.user = value;
    }

    /**
     * Obtient la valeur de la propriété validator.
     * 
     */
    public int getValidator() {
        return validator;
    }

    /**
     * Définit la valeur de la propriété validator.
     * 
     */
    public void setValidator(int value) {
        this.validator = value;
    }

}
