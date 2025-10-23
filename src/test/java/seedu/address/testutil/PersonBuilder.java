package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.Email;
import seedu.address.model.person.EmergencyContact;
import seedu.address.model.person.Name;
import seedu.address.model.person.Organ;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Priority;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ORGAN = "kidney";
    public static final String DEFAULT_BLOODTYPE = "O+";
    public static final Integer DEFAULT_PRIORITY = 1;

    private Name name;
    private Phone phone;
    private Email email;
    private EmergencyContact emergencyContact;
    private Address address;
    private Organ organ;
    private BloodType bloodType;
    private Set<Tag> tags;
    private Priority priority;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        organ = new Organ(DEFAULT_ORGAN);
        bloodType = new BloodType(DEFAULT_BLOODTYPE);
        tags = new HashSet<>();
        priority = new Priority(DEFAULT_PRIORITY);
        emergencyContact = null;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        organ = personToCopy.getOrgan();
        bloodType = personToCopy.getBloodType();
        tags = new HashSet<>(personToCopy.getTags());
        priority = personToCopy.getPriority();
        emergencyContact = personToCopy.getEmergencyContact();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Organ} of the {@code Person} that we are building.
     */
    public PersonBuilder withOrgan(String organ) {
        this.organ = new Organ(organ);
        return this;
    }
    /**
     * Sets the {@code BloodType} of the {@code Person} that we are building.
     */
    public PersonBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriority(Integer priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code EmergencyContact} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergencyContact(EmergencyContact emergencyContact) {
        this.emergencyContact = emergencyContact;
        return this;
    }

    /**
     * Sets the {@code EmergencyContact} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmergencyContact(String name, String phone, String relationship) {
        this.emergencyContact = new EmergencyContact(new Name(name), new Phone(phone), relationship);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, organ, bloodType, priority, tags, emergencyContact);
    }

}
