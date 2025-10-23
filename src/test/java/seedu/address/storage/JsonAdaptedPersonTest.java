package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Organ;
import seedu.address.model.person.Phone;


public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_EMERGENCY_NAME = "John&Emergency";
    private static final String INVALID_EMERGENCY_PHONE = "911a";
    private static final String INVALID_ORGAN = "k1dney";
    private static final String INVALID_TAG = "#friend";
    private static final Integer INVALID_PRIORITY = -1;
    private static final String INVALID_BLOODTYPE = "Z+";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_EMERGENCY_NAME = "John Emergency";
    private static final String VALID_EMERGENCY_PHONE = "91234567";
    private static final String VALID_EMERGENCY_RELATION = "spouse";
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_ORGAN = BENSON.getOrgan().toString();
    private static final String VALID_BLOODTYPE = BENSON.getBloodType().toString();
    private static final Integer VALID_PRIORITY = 1;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullOrgan_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                null, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Organ.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidOrgan_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = Organ.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORGAN,
                        VALID_BLOODTYPE, VALID_PRIORITY, invalidTags, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        assertThrows(IllegalValueException.class, person::toModelType);
    }
    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, null, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                VALID_EMERGENCY_RELATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_ORGAN,
                        INVALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, VALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE,
                        VALID_EMERGENCY_RELATION);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_emergencyContactNameOnlyProvided_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS,
                VALID_EMERGENCY_NAME, null, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "EmergencyContact Phone");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_emergencyContactPhoneOnlyProvided_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS,
                null, VALID_EMERGENCY_PHONE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "EmergencyContact Name");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmergencyContactName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS,
                INVALID_EMERGENCY_NAME, VALID_EMERGENCY_PHONE, VALID_EMERGENCY_RELATION);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmergencyContactPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS,
                VALID_EMERGENCY_NAME, INVALID_EMERGENCY_PHONE, VALID_EMERGENCY_RELATION);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_noEmergencyContact_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_ORGAN, VALID_BLOODTYPE, VALID_PRIORITY, VALID_TAGS, null, null, null);
        person.toModelType();
    }
}
