package occi.http.check;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

public class OcciParser extends Parser {
	public static final String[] tokenNames = new String[] { "<invalid>",
			"<EOR>", "<DOWN>", "<UP>", "TERM_VALUE", "QUOTED_VALUE",
			"TARGET_VALUE", "DIGITS", "FLOAT", "URL", "QUOTE", "WS",
			"'Category'", "':'", "','", "';'", "'scheme'", "'='", "'class'",
			"'title'", "'rel'", "'location'", "'attributes'", "'actions'",
			"'Link'", "'<'", "'?action='", "'>'", "'self'", "'category'",
			"'X-OCCI-Attribute'", "'X-OCCI-Location'" };
	public static final int EOF = -1;
	public static final int T__12 = 12;
	public static final int T__13 = 13;
	public static final int T__14 = 14;
	public static final int T__15 = 15;
	public static final int T__16 = 16;
	public static final int T__17 = 17;
	public static final int T__18 = 18;
	public static final int T__19 = 19;
	public static final int T__20 = 20;
	public static final int T__21 = 21;
	public static final int T__22 = 22;
	public static final int T__23 = 23;
	public static final int T__24 = 24;
	public static final int T__25 = 25;
	public static final int T__26 = 26;
	public static final int T__27 = 27;
	public static final int T__28 = 28;
	public static final int T__29 = 29;
	public static final int T__30 = 30;
	public static final int T__31 = 31;
	public static final int TERM_VALUE = 4;
	public static final int QUOTED_VALUE = 5;
	public static final int TARGET_VALUE = 6;
	public static final int DIGITS = 7;
	public static final int FLOAT = 8;
	public static final int URL = 9;
	public static final int QUOTE = 10;
	public static final int WS = 11;

	// delegates
	// delegators

	public OcciParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public OcciParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}

	@Override
	public String[] getTokenNames() {
		return OcciParser.tokenNames;
	}

	@Override
	public String getGrammarFileName() {
		return "/Occi.g";
	}

	public static String occi_categories = "occi.categories";
	public static String occi_links = "occi.links";
	public static String occi_attributes = "occi.attributes";
	public static String occi_locations = "occi.locations";
	static String occi_core_term = "occi.core.term";
	static String occi_core_scheme = "occi.core.scheme";
	static String occi_core_class = "occi.core.class";
	static String occi_core_class_kind = "kind";
	static String occi_core_class_mixin = "mixin";
	static String occi_core_class_action = "action";
	static String occi_core_title = "occi.core.title";
	static String occi_core_rel = "occi.core.rel";
	static String occi_core_location = "occi.core.location";
	static String occi_core_attributes = "occi.core.attributes";
	static String occi_core_actions = "occi.core.actions";
	static String occi_core_target = "occi.core.target";
	static String occi_core_actionterm = "occi.core.actionterm";
	static String occi_core_self = "occi.core.self";
	static String occi_core_category = "occi.core.category";

	private String last_error = "";

	// private HashMap allheaders = new HashMap();

	public static OcciParser getParser(String occiHeader) throws Exception {

		CharStream stream = new ANTLRStringStream(occiHeader);
		OcciLexer lexer = new OcciLexer(stream);
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);
		OcciParser parser = new OcciParser(tokenStream);

		return parser;
	}

	public String getLastError() {
		return last_error;
	}

	private String removeQuotes(String cleanMe) {

		if (cleanMe.matches("^(\"|').*(\"|')$"))
			cleanMe = cleanMe.substring(1, cleanMe.length() - 1);

		return cleanMe;
	}

	// $ANTLR start "headers"
	// headers returns [HashMap value] : ( category | link | attribute |
	// location )* ;
	public final HashMap headers() throws RecognitionException {
		HashMap value = null;

		ArrayList category1 = null;

		ArrayList link2 = null;

		HashMap attribute3 = null;

		ArrayList location4 = null;

		try {
			// ( ( category | link | attribute | location )* )
			// ( category | link | attribute | location )*
			{

				value = new HashMap();
				ArrayList catList = new ArrayList();
				ArrayList linkList = new ArrayList();
				ArrayList attrList = new ArrayList();
				ArrayList locList = new ArrayList();

				// ( category | link | attribute | location )*
				loop1: do {
					int alt1 = 5;
					switch (input.LA(1)) {
					case 12: {
						alt1 = 1;
					}
						break;
					case 24: {
						alt1 = 2;
					}
						break;
					case 30: {
						alt1 = 3;
					}
						break;
					case 31: {
						alt1 = 4;
					}
						break;

					}

					switch (alt1) {
					case 1:
					// category
					{
						pushFollow(FOLLOW_category_in_headers152);
						category1 = category();

						state._fsp--;

						if (category1 != null)
							catList.add(category1);

					}
						break;
					case 2:
					// link
					{
						pushFollow(FOLLOW_link_in_headers186);
						link2 = link();

						state._fsp--;

						if (link2 != null)
							linkList.add(link2);

					}
						break;
					case 3:
					// attribute
					{
						pushFollow(FOLLOW_attribute_in_headers224);
						attribute3 = attribute();

						state._fsp--;

						if (attribute3 != null)
							attrList.add(attribute3);

					}
						break;
					case 4:
					// location
					{
						pushFollow(FOLLOW_location_in_headers257);
						location4 = location();

						state._fsp--;

						if (location4 != null)
							locList.add(location4);

					}
						break;

					default:
						break loop1;
					}
				} while (true);

				value.put(occi_categories, catList);
				value.put(occi_links, linkList);
				value.put(occi_attributes, attrList);
				value.put(occi_locations, locList);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "headers"

	// $ANTLR start "category"
	// category returns [ArrayList cats] : 'Category' ':' category_values ;
	public final ArrayList category() throws RecognitionException {
		ArrayList cats = null;

		ArrayList category_values5 = null;

		try {
			// ( 'Category' ':' category_values )
			// 'Category' ':' category_values
			{
				match(input, 12, FOLLOW_12_in_category395);
				match(input, 13, FOLLOW_13_in_category397);
				pushFollow(FOLLOW_category_values_in_category424);
				category_values5 = category_values();

				state._fsp--;

				cats = category_values5;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return cats;
	}

	// $ANTLR end "category"

	// $ANTLR start "category_values"
	// category_values returns [ArrayList cats] : cv1= category_value ( ',' cv2=
	// category_value )* ;
	public final ArrayList category_values() throws RecognitionException {
		ArrayList cats = null;

		HashMap cv1 = null;

		HashMap cv2 = null;

		try {
			// (cv1= category_value ( ',' cv2= category_value )* )
			// cv1= category_value ( ',' cv2= category_value )*
			{
				pushFollow(FOLLOW_category_value_in_category_values496);
				cv1 = category_value();

				state._fsp--;

				cats = new ArrayList();
				cats.add(cv1);

				// ( ',' cv2= category_value )*
				loop2: do {
					int alt2 = 2;
					int LA2_0 = input.LA(1);

					if ((LA2_0 == 14)) {
						alt2 = 1;
					}

					switch (alt2) {
					case 1:
					// ',' cv2= category_value
					{
						match(input, 14, FOLLOW_14_in_category_values549);
						pushFollow(FOLLOW_category_value_in_category_values553);
						cv2 = category_value();

						state._fsp--;

						cats.add(cv2);

					}
						break;

					default:
						break loop2;
					}
				} while (true);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return cats;
	}

	// $ANTLR end "category_values"

	// $ANTLR start "category_value"
	// category_value returns [HashMap cat] : term_attr scheme_attr klass_attr (
	// title_attr )? ( rel_attr )? ( location_attr )? ( c_attributes_attr )? (
	// actions_attr )? ;
	public final HashMap category_value() throws RecognitionException {
		HashMap cat = null;

		String term_attr6 = null;

		String scheme_attr7 = null;

		String klass_attr8 = null;

		String title_attr9 = null;

		String rel_attr10 = null;

		String location_attr11 = null;

		String c_attributes_attr12 = null;

		String actions_attr13 = null;

		try {
			// ( term_attr scheme_attr klass_attr ( title_attr )? ( rel_attr )?
			// ( location_attr )? ( c_attributes_attr )? ( actions_attr )? )
			// term_attr scheme_attr klass_attr ( title_attr )? ( rel_attr )? (
			// location_attr )? ( c_attributes_attr )? ( actions_attr )?
			{
				pushFollow(FOLLOW_term_attr_in_category_value650);
				term_attr6 = term_attr();

				state._fsp--;

				pushFollow(FOLLOW_scheme_attr_in_category_value652);
				scheme_attr7 = scheme_attr();

				state._fsp--;

				pushFollow(FOLLOW_klass_attr_in_category_value654);
				klass_attr8 = klass_attr();

				state._fsp--;

				// ( title_attr )?
				int alt3 = 2;
				int LA3_0 = input.LA(1);

				if ((LA3_0 == 15)) {
					int LA3_1 = input.LA(2);

					if ((LA3_1 == 19)) {
						alt3 = 1;
					}
				}
				switch (alt3) {
				case 1:
				// title_attr
				{
					pushFollow(FOLLOW_title_attr_in_category_value656);
					title_attr9 = title_attr();

					state._fsp--;

				}
					break;

				}

				// ( rel_attr )?
				int alt4 = 2;
				int LA4_0 = input.LA(1);

				if ((LA4_0 == 15)) {
					int LA4_1 = input.LA(2);

					if ((LA4_1 == 20)) {
						alt4 = 1;
					}
				}
				switch (alt4) {
				case 1:
				// rel_attr
				{
					pushFollow(FOLLOW_rel_attr_in_category_value659);
					rel_attr10 = rel_attr();

					state._fsp--;

				}
					break;

				}

				// ( location_attr )?
				int alt5 = 2;
				int LA5_0 = input.LA(1);

				if ((LA5_0 == 15)) {
					int LA5_1 = input.LA(2);

					if ((LA5_1 == 21)) {
						alt5 = 1;
					}
				}
				switch (alt5) {
				case 1:
				// location_attr
				{
					pushFollow(FOLLOW_location_attr_in_category_value662);
					location_attr11 = location_attr();

					state._fsp--;

				}
					break;

				}

				// ( c_attributes_attr )?
				int alt6 = 2;
				int LA6_0 = input.LA(1);

				if ((LA6_0 == 15)) {
					int LA6_1 = input.LA(2);

					if ((LA6_1 == 22)) {
						alt6 = 1;
					}
				}
				switch (alt6) {
				case 1:
				// c_attributes_attr
				{
					pushFollow(FOLLOW_c_attributes_attr_in_category_value665);
					c_attributes_attr12 = c_attributes_attr();

					state._fsp--;

				}
					break;

				}

				// ( actions_attr )?
				int alt7 = 2;
				int LA7_0 = input.LA(1);

				if ((LA7_0 == 15)) {
					alt7 = 1;
				}
				switch (alt7) {
				case 1:
				// actions_attr
				{
					pushFollow(FOLLOW_actions_attr_in_category_value668);
					actions_attr13 = actions_attr();

					state._fsp--;

				}
					break;

				}

				cat = new HashMap();

				cat.put(occi_core_term, term_attr6);
				cat.put(occi_core_scheme, scheme_attr7);
				cat.put(occi_core_class, klass_attr8);

				if (title_attr9 != null)
					cat.put(occi_core_title, title_attr9);

				if (rel_attr10 != null)
					cat.put(occi_core_rel, rel_attr10);

				if (location_attr11 != null)
					cat.put(occi_core_location, location_attr11);

				if (c_attributes_attr12 != null)
					cat.put(occi_core_attributes, c_attributes_attr12);

				if (actions_attr13 != null)
					cat.put(occi_core_actions, actions_attr13);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return cat;
	}

	// $ANTLR end "category_value"

	// $ANTLR start "term_attr"
	// term_attr returns [String value] : TERM_VALUE ;
	public final String term_attr() throws RecognitionException {
		String value = null;

		Token TERM_VALUE14 = null;

		try {
			// ( TERM_VALUE )
			// TERM_VALUE
			{
				TERM_VALUE14 = (Token) match(input, TERM_VALUE,
						FOLLOW_TERM_VALUE_in_term_attr745);

				value = (TERM_VALUE14 != null ? TERM_VALUE14.getText() : null);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "term_attr"

	// $ANTLR start "scheme_attr"
	// /Users/andy/Source/antlr/occi-grammar/occi-antlr-java/Occi.g:189:1:
	// scheme_attr returns [String value] : ';' 'scheme' '=' QUOTED_VALUE ;
	public final String scheme_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE15 = null;

		try {
			// ( ';' 'scheme' '=' QUOTED_VALUE )
			// ';' 'scheme' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_scheme_attr819);
				match(input, 16, FOLLOW_16_in_scheme_attr821);
				match(input, 17, FOLLOW_17_in_scheme_attr823);
				QUOTED_VALUE15 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_scheme_attr849);

				value = removeQuotes((QUOTED_VALUE15 != null ? QUOTED_VALUE15
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "scheme_attr"

	// $ANTLR start "klass_attr"
	// klass_attr returns [String value] : ';' 'class' '=' QUOTED_VALUE ;
	public final String klass_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE16 = null;

		try {
			// ( ';' 'class' '=' QUOTED_VALUE )
			// ';' 'class' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_klass_attr923);
				match(input, 18, FOLLOW_18_in_klass_attr925);
				match(input, 17, FOLLOW_17_in_klass_attr927);
				QUOTED_VALUE16 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_klass_attr953);

				String klass = removeQuotes((QUOTED_VALUE16 != null ? QUOTED_VALUE16
						.getText() : null));

				if (!(klass.equals(occi_core_class_kind)
						|| klass.equals(occi_core_class_mixin) || klass
							.equals(occi_core_class_action))) {
					System.out
							.println("the 'class' attribute's value can only be ['kind', 'mixin', 'action']");
					// throw new
					// OcciParserException("the 'class' attribute's value can only be ['kind', 'mixin', 'action']");
				}
				value = klass;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "klass_attr"

	// $ANTLR start "title_attr"
	// title_attr returns [String value] : ';' 'title' '=' QUOTED_VALUE ;
	public final String title_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE17 = null;

		try {
			// ( ';' 'title' '=' QUOTED_VALUE )
			// ';' 'title' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_title_attr1027);
				match(input, 19, FOLLOW_19_in_title_attr1029);
				match(input, 17, FOLLOW_17_in_title_attr1031);
				QUOTED_VALUE17 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_title_attr1057);

				value = removeQuotes((QUOTED_VALUE17 != null ? QUOTED_VALUE17
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "title_attr"

	// $ANTLR start "rel_attr"
	// rel_attr returns [String value] : ';' 'rel' '=' QUOTED_VALUE ;
	public final String rel_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE18 = null;

		try {
			// ( ';' 'rel' '=' QUOTED_VALUE )
			// ';' 'rel' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_rel_attr1134);
				match(input, 20, FOLLOW_20_in_rel_attr1136);
				match(input, 17, FOLLOW_17_in_rel_attr1138);
				QUOTED_VALUE18 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_rel_attr1164);

				value = removeQuotes((QUOTED_VALUE18 != null ? QUOTED_VALUE18
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "rel_attr"

	// $ANTLR start "location_attr"
	// location_attr returns [String value] : ';' 'location' '=' TARGET_VALUE ;
	public final String location_attr() throws RecognitionException {
		String value = null;

		Token TARGET_VALUE19 = null;

		try {
			// ( ';' 'location' '=' TARGET_VALUE )
			// ';' 'location' '=' TARGET_VALUE
			{
				match(input, 15, FOLLOW_15_in_location_attr1237);
				match(input, 21, FOLLOW_21_in_location_attr1239);
				match(input, 17, FOLLOW_17_in_location_attr1241);
				TARGET_VALUE19 = (Token) match(input, TARGET_VALUE,
						FOLLOW_TARGET_VALUE_in_location_attr1267);

				value = (TARGET_VALUE19 != null ? TARGET_VALUE19.getText()
						: null);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "location_attr"

	// $ANTLR start "c_attributes_attr"
	// /Users/andy/Source/antlr/occi-grammar/occi-antlr-java/Occi.g:236:1:
	// c_attributes_attr returns [String value] : ';' 'attributes' '='
	// QUOTED_VALUE ;
	public final String c_attributes_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE20 = null;

		try {
			// ( ';' 'attributes' '=' QUOTED_VALUE )
			// ';' 'attributes' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_c_attributes_attr1335);
				match(input, 22, FOLLOW_22_in_c_attributes_attr1337);
				match(input, 17, FOLLOW_17_in_c_attributes_attr1339);
				QUOTED_VALUE20 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_c_attributes_attr1365);

				value = removeQuotes((QUOTED_VALUE20 != null ? QUOTED_VALUE20
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "c_attributes_attr"

	// $ANTLR start "actions_attr"
	// actions_attr returns [String value] : ';' 'actions' '=' QUOTED_VALUE ;
	public final String actions_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE21 = null;

		try {
			// ( ';' 'actions' '=' QUOTED_VALUE )
			// ';' 'actions' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_actions_attr1438);
				match(input, 23, FOLLOW_23_in_actions_attr1440);
				match(input, 17, FOLLOW_17_in_actions_attr1442);
				QUOTED_VALUE21 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_actions_attr1468);

				value = removeQuotes((QUOTED_VALUE21 != null ? QUOTED_VALUE21
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "actions_attr"

	// $ANTLR start "link"
	// returns [ArrayList link] : 'Link' ':' link_values ;
	public final ArrayList link() throws RecognitionException {
		ArrayList link = null;

		ArrayList link_values22 = null;

		try {
			// ( 'Link' ':' link_values )
			// 'Link' ':' link_values
			{
				match(input, 24, FOLLOW_24_in_link1552);
				match(input, 13, FOLLOW_13_in_link1554);
				pushFollow(FOLLOW_link_values_in_link1582);
				link_values22 = link_values();

				state._fsp--;

				link = link_values22;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return link;
	}

	// $ANTLR end "link"

	// $ANTLR start "link_values"
	// link_values returns [ArrayList links] : lv1= link_value ( ',' lv2=
	// link_value )* ;
	public final ArrayList link_values() throws RecognitionException {
		ArrayList links = null;

		HashMap lv1 = null;

		HashMap lv2 = null;

		try {
			// (lv1= link_value ( ',' lv2= link_value )* )
			// lv1= link_value ( ',' lv2= link_value )*
			{
				pushFollow(FOLLOW_link_value_in_link_values1661);
				lv1 = link_value();

				state._fsp--;

				links = new ArrayList();
				links.add(lv1);

				// ( ',' lv2= link_value )*
				loop8: do {
					int alt8 = 2;
					int LA8_0 = input.LA(1);

					if ((LA8_0 == 14)) {
						alt8 = 1;
					}

					switch (alt8) {
					case 1:
					// ',' lv2= link_value
					{
						match(input, 14, FOLLOW_14_in_link_values1719);
						pushFollow(FOLLOW_link_value_in_link_values1723);
						lv2 = link_value();

						state._fsp--;

						links.add(lv2);

					}
						break;

					default:
						break loop8;
					}
				} while (true);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return links;
	}

	// $ANTLR end "link_values"

	// $ANTLR start "link_value"
	// link_value returns [HashMap linkAttrs] : target_attr rel_attr ( self_attr
	// )? ( category_attr )? ( attribute_attr )? ;
	public final HashMap link_value() throws RecognitionException {
		HashMap linkAttrs = null;

		ArrayList target_attr23 = null;

		String rel_attr24 = null;

		String self_attr25 = null;

		String category_attr26 = null;

		HashMap attribute_attr27 = null;

		try {
			// ( target_attr rel_attr ( self_attr )? ( category_attr )? (
			// attribute_attr )? )
			// target_attr rel_attr ( self_attr )? ( category_attr )? (
			// attribute_attr )?
			{
				pushFollow(FOLLOW_target_attr_in_link_value1827);
				target_attr23 = target_attr();

				state._fsp--;

				pushFollow(FOLLOW_rel_attr_in_link_value1829);
				rel_attr24 = rel_attr();

				state._fsp--;

				// ( self_attr )?
				int alt9 = 2;
				int LA9_0 = input.LA(1);

				if ((LA9_0 == 15)) {
					int LA9_1 = input.LA(2);

					if ((LA9_1 == 28)) {
						alt9 = 1;
					}
				}
				switch (alt9) {
				case 1:
				// self_attr
				{
					pushFollow(FOLLOW_self_attr_in_link_value1831);
					self_attr25 = self_attr();

					state._fsp--;

				}
					break;

				}

				// ( category_attr )?
				int alt10 = 2;
				int LA10_0 = input.LA(1);

				if ((LA10_0 == 15)) {
					int LA10_1 = input.LA(2);

					if ((LA10_1 == 29)) {
						alt10 = 1;
					}
				}
				switch (alt10) {
				case 1:
				// category_attr
				{
					pushFollow(FOLLOW_category_attr_in_link_value1834);
					category_attr26 = category_attr();

					state._fsp--;

				}
					break;

				}

				// ( attribute_attr )?
				int alt11 = 2;
				int LA11_0 = input.LA(1);

				if ((LA11_0 == 15)) {
					alt11 = 1;
				}
				switch (alt11) {
				case 1:
				// attribute_attr
				{
					pushFollow(FOLLOW_attribute_attr_in_link_value1837);
					attribute_attr27 = attribute_attr();

					state._fsp--;

				}
					break;

				}

				linkAttrs = new HashMap();

				linkAttrs.put(occi_core_target, target_attr23.get(0));
				if (target_attr23.size() == 2)
					linkAttrs.put(occi_core_actionterm, target_attr23.get(1));

				linkAttrs.put(occi_core_rel, rel_attr24);

				if (self_attr25 != null)
					linkAttrs.put(occi_core_self, self_attr25);

				if (category_attr26 != null)
					linkAttrs.put(occi_core_category, category_attr26);

				if (attribute_attr27 != null)
					linkAttrs.putAll(attribute_attr27);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return linkAttrs;
	}

	// $ANTLR end "link_value"

	// $ANTLR start "target_attr"
	// target_attr returns [ArrayList targetAndTerm] : '<' TARGET_VALUE (
	// '?action=' TERM_VALUE )? '>' ;
	public final ArrayList target_attr() throws RecognitionException {
		ArrayList targetAndTerm = null;

		Token TARGET_VALUE28 = null;
		Token TERM_VALUE29 = null;

		try {
			// ( '<' TARGET_VALUE ( '?action=' TERM_VALUE )? '>' )
			// '<' TARGET_VALUE ( '?action=' TERM_VALUE )? '>'
			{
				match(input, 25, FOLLOW_25_in_target_attr1939);
				TARGET_VALUE28 = (Token) match(input, TARGET_VALUE,
						FOLLOW_TARGET_VALUE_in_target_attr1941);

				targetAndTerm = new ArrayList();
				targetAndTerm.add((TARGET_VALUE28 != null ? TARGET_VALUE28
						.getText() : null));

				// ( '?action=' TERM_VALUE )?
				int alt12 = 2;
				int LA12_0 = input.LA(1);

				if ((LA12_0 == 26)) {
					alt12 = 1;
				}
				switch (alt12) {
				case 1:
				// '?action=' TERM_VALUE
				{
					match(input, 26, FOLLOW_26_in_target_attr1971);
					TERM_VALUE29 = (Token) match(input, TERM_VALUE,
							FOLLOW_TERM_VALUE_in_target_attr2000);

					targetAndTerm.add((TERM_VALUE29 != null ? TERM_VALUE29
							.getText() : null));

				}
					break;

				}

				match(input, 27, FOLLOW_27_in_target_attr2031);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return targetAndTerm;
	}

	// $ANTLR end "target_attr"

	// $ANTLR start "self_attr"
	// self_attr returns [String value] : ';' 'self' '=' QUOTED_VALUE ;
	public final String self_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE30 = null;

		try {
			// ( ';' 'self' '=' QUOTED_VALUE )
			// ';' 'self' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_self_attr2107);
				match(input, 28, FOLLOW_28_in_self_attr2109);
				match(input, 17, FOLLOW_17_in_self_attr2111);
				QUOTED_VALUE30 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_self_attr2138);

				value = removeQuotes((QUOTED_VALUE30 != null ? QUOTED_VALUE30
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "self_attr"

	// $ANTLR start "category_attr"
	// category_attr returns [String value] : ';' 'category' '=' QUOTED_VALUE ;
	public final String category_attr() throws RecognitionException {
		String value = null;

		Token QUOTED_VALUE31 = null;

		try {
			// ( ';' 'category' '=' QUOTED_VALUE )
			// ';' 'category' '=' QUOTED_VALUE
			{
				match(input, 15, FOLLOW_15_in_category_attr2211);
				match(input, 29, FOLLOW_29_in_category_attr2213);
				match(input, 17, FOLLOW_17_in_category_attr2215);
				QUOTED_VALUE31 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_category_attr2242);

				value = removeQuotes((QUOTED_VALUE31 != null ? QUOTED_VALUE31
						.getText() : null));

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "category_attr"

	// $ANTLR start "attribute_attr"
	// attribute_attr returns [HashMap attr] : ';' attributes_attr ;
	public final HashMap attribute_attr() throws RecognitionException {
		HashMap attr = null;

		HashMap attributes_attr32 = null;

		try {
			// ( ';' attributes_attr )
			// ';' attributes_attr
			{
				match(input, 15, FOLLOW_15_in_attribute_attr2314);
				pushFollow(FOLLOW_attributes_attr_in_attribute_attr2316);
				attributes_attr32 = attributes_attr();

				state._fsp--;

				attr = attributes_attr32;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return attr;
	}

	// $ANTLR end "attribute_attr"

	// $ANTLR start "attributes_attr"
	// attributes_attr returns [HashMap attrs] : kv1= attribute_kv_attr ( ','
	// kv2= attribute_kv_attr )* ;
	public final HashMap attributes_attr() throws RecognitionException {
		HashMap attrs = null;

		ArrayList kv1 = null;

		ArrayList kv2 = null;

		try {
			// (kv1= attribute_kv_attr ( ',' kv2= attribute_kv_attr )* )
			// kv1= attribute_kv_attr ( ',' kv2= attribute_kv_attr )*
			{
				pushFollow(FOLLOW_attribute_kv_attr_in_attributes_attr2389);
				kv1 = attribute_kv_attr();

				state._fsp--;

				attrs = new HashMap();
				attrs.put(kv1.get(0), kv1.get(1));

				// ( ',' kv2= attribute_kv_attr )*
				loop13: do {
					int alt13 = 2;
					int LA13_0 = input.LA(1);

					if ((LA13_0 == 14)) {
						int LA13_1 = input.LA(2);

						if ((LA13_1 == TERM_VALUE)) {
							alt13 = 1;
						}

					}

					switch (alt13) {
					case 1:
					// ',' kv2= attribute_kv_attr
					{
						match(input, 14, FOLLOW_14_in_attributes_attr2447);
						pushFollow(FOLLOW_attribute_kv_attr_in_attributes_attr2451);
						kv2 = attribute_kv_attr();

						state._fsp--;

						attrs.put(kv2.get(0), kv2.get(1));

					}
						break;

					default:
						break loop13;
					}
				} while (true);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return attrs;
	}

	// $ANTLR end "attributes_attr"

	// $ANTLR start "attribute_kv_attr"
	// attribute_kv_attr returns [ArrayList keyval] : attribute_name_attr '='
	// attribute_value_attr ;
	public final ArrayList attribute_kv_attr() throws RecognitionException {
		ArrayList keyval = null;

		OcciParser.attribute_name_attr_return attribute_name_attr33 = null;

		Object attribute_value_attr34 = null;

		try {
			// ( attribute_name_attr '=' attribute_value_attr )
			// attribute_name_attr '=' attribute_value_attr
			{
				pushFollow(FOLLOW_attribute_name_attr_in_attribute_kv_attr2549);
				attribute_name_attr33 = attribute_name_attr();

				state._fsp--;

				match(input, 17, FOLLOW_17_in_attribute_kv_attr2551);
				pushFollow(FOLLOW_attribute_value_attr_in_attribute_kv_attr2553);
				attribute_value_attr34 = attribute_value_attr();

				state._fsp--;

				keyval = new ArrayList();
				keyval.add((attribute_name_attr33 != null ? input
						.toString(attribute_name_attr33.start,
								attribute_name_attr33.stop) : null));
				keyval.add(attribute_value_attr34);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return keyval;
	}

	// $ANTLR end "attribute_kv_attr"

	public static class attribute_name_attr_return extends
			ParserRuleReturnScope {
	};

	// $ANTLR start "attribute_name_attr"
	// attribute_name_attr : TERM_VALUE ;
	public final OcciParser.attribute_name_attr_return attribute_name_attr()
			throws RecognitionException {
		OcciParser.attribute_name_attr_return retval = new OcciParser.attribute_name_attr_return();
		retval.start = input.LT(1);

		try {
			// ( TERM_VALUE )
			// TERM_VALUE
			{
				match(input, TERM_VALUE,
						FOLLOW_TERM_VALUE_in_attribute_name_attr2593);

			}

			retval.stop = input.LT(-1);

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return retval;
	}

	// $ANTLR end "attribute_name_attr"

	// $ANTLR start "attribute_value_attr"
	// attribute_value_attr returns [Object value] : ( QUOTED_VALUE | DIGITS |
	// FLOAT );
	public final Object attribute_value_attr() throws RecognitionException {
		Object value = null;

		Token QUOTED_VALUE35 = null;
		Token DIGITS36 = null;
		Token FLOAT37 = null;

		try {
			// ( QUOTED_VALUE | DIGITS | FLOAT )
			int alt14 = 3;
			switch (input.LA(1)) {
			case QUOTED_VALUE: {
				alt14 = 1;
			}
				break;
			case DIGITS: {
				alt14 = 2;
			}
				break;
			case FLOAT: {
				alt14 = 3;
			}
				break;
			default:
				NoViableAltException nvae = new NoViableAltException("", 14, 0,
						input);

				throw nvae;
			}

			switch (alt14) {
			case 1:
			// QUOTED_VALUE
			{
				QUOTED_VALUE35 = (Token) match(input, QUOTED_VALUE,
						FOLLOW_QUOTED_VALUE_in_attribute_value_attr2631);

				value = removeQuotes((QUOTED_VALUE35 != null ? QUOTED_VALUE35
						.getText() : null));

			}
				break;
			case 2:
			// DIGITS
			{
				DIGITS36 = (Token) match(input, DIGITS,
						FOLLOW_DIGITS_in_attribute_value_attr2661);

				value = Integer
						.parseInt(removeQuotes((DIGITS36 != null ? DIGITS36
								.getText() : null)));

			}
				break;
			case 3:
			// FLOAT
			{
				FLOAT37 = (Token) match(input, FLOAT,
						FOLLOW_FLOAT_in_attribute_value_attr2691);

				value = new BigDecimal(
						removeQuotes((FLOAT37 != null ? FLOAT37.getText()
								: null)));

			}
				break;

			}
		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return value;
	}

	// $ANTLR end "attribute_value_attr"

	// $ANTLR start "attribute"
	// attribute returns [HashMap attrs] : 'X-OCCI-Attribute' ':'
	// attributes_attr ;
	public final HashMap attribute() throws RecognitionException {
		HashMap attrs = null;

		HashMap attributes_attr38 = null;

		try {
			// ( 'X-OCCI-Attribute' ':' attributes_attr )
			// 'X-OCCI-Attribute' ':' attributes_attr
			{
				match(input, 30, FOLLOW_30_in_attribute2770);
				match(input, 13, FOLLOW_13_in_attribute2772);
				pushFollow(FOLLOW_attributes_attr_in_attribute2799);
				attributes_attr38 = attributes_attr();

				state._fsp--;

				attrs = attributes_attr38;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return attrs;
	}

	// $ANTLR end "attribute"

	// $ANTLR start "location"
	// location returns [ArrayList urls] : 'X-OCCI-Location' ':' location_values
	// ;
	public final ArrayList location() throws RecognitionException {
		ArrayList urls = null;

		ArrayList location_values39 = null;

		try {
			// ( 'X-OCCI-Location' ':' location_values )
			// 'X-OCCI-Location' ':' location_values
			{
				match(input, 31, FOLLOW_31_in_location2878);
				match(input, 13, FOLLOW_13_in_location2880);
				pushFollow(FOLLOW_location_values_in_location2907);
				location_values39 = location_values();

				state._fsp--;

				urls = location_values39;

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return urls;
	}

	// $ANTLR end "location"

	// $ANTLR start "location_values"
	// location_values returns [ArrayList urls] : u1= URL ( ',' u2= URL )* ;
	public final ArrayList location_values() throws RecognitionException {
		ArrayList urls = null;

		Token u1 = null;
		Token u2 = null;

		try {
			// (u1= URL ( ',' u2= URL )* )
			// u1= URL ( ',' u2= URL )*
			{
				u1 = (Token) match(input, URL,
						FOLLOW_URL_in_location_values2978);

				urls = new ArrayList();
				urls.add((u1 != null ? u1.getText() : null));

				// ( ',' u2= URL )*
				loop15: do {
					int alt15 = 2;
					int LA15_0 = input.LA(1);

					if ((LA15_0 == 14)) {
						alt15 = 1;
					}

					switch (alt15) {
					case 1:
					// ',' u2= URL
					{
						match(input, 14, FOLLOW_14_in_location_values3034);
						u2 = (Token) match(input, URL,
								FOLLOW_URL_in_location_values3064);

						urls.add((u2 != null ? u2.getText() : null));

					}
						break;

					default:
						break loop15;
					}
				} while (true);

			}

		}

		catch (RecognitionException rex) {

			last_error = getErrorHeader(rex) + " "
					+ getErrorMessage(rex, OcciParser.tokenNames);
			// System.out.println("Parser error: " + last_error);

			throw new OcciParserException(last_error);
		} finally {
		}
		return urls;
	}

	// $ANTLR end "location_values"

	// Delegated rules

	public static final BitSet FOLLOW_category_in_headers152 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_link_in_headers186 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_attribute_in_headers224 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_location_in_headers257 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_12_in_category395 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_category397 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_category_values_in_category424 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_category_value_in_category_values496 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_category_values549 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_category_value_in_category_values553 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_term_attr_in_category_value650 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_scheme_attr_in_category_value652 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_klass_attr_in_category_value654 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_title_attr_in_category_value656 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_rel_attr_in_category_value659 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_location_attr_in_category_value662 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_c_attributes_attr_in_category_value665 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_actions_attr_in_category_value668 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TERM_VALUE_in_term_attr745 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_scheme_attr819 = new BitSet(
			new long[] { 0x0000000000010000L });
	public static final BitSet FOLLOW_16_in_scheme_attr821 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_scheme_attr823 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_scheme_attr849 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_klass_attr923 = new BitSet(
			new long[] { 0x0000000000040000L });
	public static final BitSet FOLLOW_18_in_klass_attr925 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_klass_attr927 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_klass_attr953 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_title_attr1027 = new BitSet(
			new long[] { 0x0000000000080000L });
	public static final BitSet FOLLOW_19_in_title_attr1029 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_title_attr1031 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_title_attr1057 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_rel_attr1134 = new BitSet(
			new long[] { 0x0000000000100000L });
	public static final BitSet FOLLOW_20_in_rel_attr1136 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_rel_attr1138 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_rel_attr1164 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_location_attr1237 = new BitSet(
			new long[] { 0x0000000000200000L });
	public static final BitSet FOLLOW_21_in_location_attr1239 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_location_attr1241 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_TARGET_VALUE_in_location_attr1267 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_c_attributes_attr1335 = new BitSet(
			new long[] { 0x0000000000400000L });
	public static final BitSet FOLLOW_22_in_c_attributes_attr1337 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_c_attributes_attr1339 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_c_attributes_attr1365 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_actions_attr1438 = new BitSet(
			new long[] { 0x0000000000800000L });
	public static final BitSet FOLLOW_23_in_actions_attr1440 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_actions_attr1442 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_actions_attr1468 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_24_in_link1552 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_link1554 = new BitSet(
			new long[] { 0x0000000002000000L });
	public static final BitSet FOLLOW_link_values_in_link1582 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_link_value_in_link_values1661 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_link_values1719 = new BitSet(
			new long[] { 0x0000000002000000L });
	public static final BitSet FOLLOW_link_value_in_link_values1723 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_target_attr_in_link_value1827 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_rel_attr_in_link_value1829 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_self_attr_in_link_value1831 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_category_attr_in_link_value1834 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_attribute_attr_in_link_value1837 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_25_in_target_attr1939 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_TARGET_VALUE_in_target_attr1941 = new BitSet(
			new long[] { 0x000000000C000000L });
	public static final BitSet FOLLOW_26_in_target_attr1971 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_TERM_VALUE_in_target_attr2000 = new BitSet(
			new long[] { 0x0000000008000000L });
	public static final BitSet FOLLOW_27_in_target_attr2031 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_self_attr2107 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_self_attr2109 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_self_attr2111 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_self_attr2138 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_category_attr2211 = new BitSet(
			new long[] { 0x0000000020000000L });
	public static final BitSet FOLLOW_29_in_category_attr2213 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_category_attr2215 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_category_attr2242 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_attribute_attr2314 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attributes_attr_in_attribute_attr2316 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_attribute_kv_attr_in_attributes_attr2389 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_attributes_attr2447 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attribute_kv_attr_in_attributes_attr2451 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_attribute_name_attr_in_attribute_kv_attr2549 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_attribute_kv_attr2551 = new BitSet(
			new long[] { 0x00000000000001A0L });
	public static final BitSet FOLLOW_attribute_value_attr_in_attribute_kv_attr2553 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TERM_VALUE_in_attribute_name_attr2593 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_attribute_value_attr2631 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_DIGITS_in_attribute_value_attr2661 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_FLOAT_in_attribute_value_attr2691 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_attribute2770 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_attribute2772 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attributes_attr_in_attribute2799 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_location2878 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_location2880 = new BitSet(
			new long[] { 0x0000000000000200L });
	public static final BitSet FOLLOW_location_values_in_location2907 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_URL_in_location_values2978 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_location_values3034 = new BitSet(
			new long[] { 0x0000000000000200L });
	public static final BitSet FOLLOW_URL_in_location_values3064 = new BitSet(
			new long[] { 0x0000000000004002L });
}