package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_RELATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORGAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_ORGAN, PREFIX_BLOODTYPE, PREFIX_PRIORITY, PREFIX_TAG,
                        PREFIX_EMERGENCY_NAME, PREFIX_EMERGENCY_PHONE, PREFIX_EMERGENCY_RELATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_ORGAN, PREFIX_BLOODTYPE, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_ORGAN,
                PREFIX_BLOODTYPE, PREFIX_PRIORITY, PREFIX_EMERGENCY_NAME, PREFIX_EMERGENCY_PHONE,
                PREFIX_EMERGENCY_RELATION);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Organ organ = ParserUtil.parseOrgan(argMultimap.getValue(PREFIX_ORGAN).get());
        BloodType bloodType = ParserUtil.parseBloodType(argMultimap.getValue(PREFIX_BLOODTYPE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        EmergencyContact emergencyContact = null;
        boolean hasEcName = argMultimap.getValue(PREFIX_EMERGENCY_NAME).isPresent();
        boolean hasEcPhone = argMultimap.getValue(PREFIX_EMERGENCY_PHONE).isPresent();

        if (hasEcName || hasEcPhone || argMultimap.getValue(PREFIX_EMERGENCY_RELATION).isPresent()) {
            if (!hasEcName || !hasEcPhone) {
                throw new ParseException(EmergencyContact.MESSAGE_CONSTRAINTS);
            }
            Name ecName = ParserUtil.parseName(argMultimap.getValue(PREFIX_EMERGENCY_NAME).get());
            Phone ecPhone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_EMERGENCY_PHONE).get());
            String ecRelation = argMultimap.getValue(PREFIX_EMERGENCY_RELATION).orElse("");
            emergencyContact = new EmergencyContact(ecName, ecPhone, ecRelation);
        }
        Priority priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());

        Person person = new Person(name, phone, email, address, organ, bloodType, priority, tagList, emergencyContact);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
