/**
 * Copyright (C) 2010-2011 Sebastian Heckmann, Sebastian Laag
 *
 * Contact Email: <sebastian.heckmann@udo.edu>, <sebastian.laag@udo.edu>
 *
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $ANTLR 3.3 Nov 30, 2010 12:45:30 /home/smsnheck/Desktop/Occi.g 2011-03-21 10:45:26

package occi.http.check;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.debug.DebugEventListener;
import org.antlr.runtime.debug.DebugEventSocketProxy;
import org.antlr.runtime.debug.DebugParser;

public class OcciParser extends DebugParser {
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

	public static final String[] ruleNames = new String[] { "invalidRule",
			"attribute_name_attr", "c_attributes_attr", "attribute_value_attr",
			"attribute_kv_attr", "link", "category_value", "category_values",
			"klass_attr", "scheme_attr", "location_attr", "location_values",
			"headers", "attribute", "self_attr", "term_attr", "target_attr",
			"location", "link_values", "category", "link_value",
			"actions_attr", "category_attr", "title_attr", "rel_attr",
			"attributes_attr", "attribute_attr" };
	public static final boolean[] decisionCanBacktrack = new boolean[] {
			false, // invalid decision
			false, false, false, false, false, false, false, false, false,
			false, false, false, false, false };

	public int ruleLevel = 0;

	public int getRuleLevel() {
		return ruleLevel;
	}

	public void incRuleLevel() {
		ruleLevel++;
	}

	public void decRuleLevel() {
		ruleLevel--;
	}

	public OcciParser(TokenStream input) {
		this(input, DebugEventSocketProxy.DEFAULT_DEBUGGER_PORT,
				new RecognizerSharedState());
	}

	public OcciParser(TokenStream input, int port, RecognizerSharedState state) {
		super(input, state);
		DebugEventSocketProxy proxy = new DebugEventSocketProxy(this, port,
				null);
		setDebugListener(proxy);
		try {
			proxy.handshake();
		} catch (IOException ioe) {
			reportError(ioe);
		}
	}

	public OcciParser(TokenStream input, DebugEventListener dbg) {
		super(input, dbg, new RecognizerSharedState());

	}

	protected boolean evalPredicate(boolean result, String predicate) {
		dbg.semanticPredicate(result, predicate);
		return result;
	}

	@Override
	public String[] getTokenNames() {
		return OcciParser.tokenNames;
	}

	@Override
	public String getGrammarFileName() {
		return "/home/smsnheck/Desktop/Occi.g";
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

		if (cleanMe.matches("^(\"|').*(\"|')$")) {
			cleanMe = cleanMe.substring(1, cleanMe.length() - 1);
		}

		return cleanMe;
	}

	// $ANTLR start "headers"
	// /home/smsnheck/Desktop/Occi.g:76:1: headers returns [HashMap value] : (
	// category | link | attribute | location )* ;
	public final HashMap headers() throws RecognitionException,
			OcciParserException {
		HashMap value = null;

		ArrayList category1 = null;

		ArrayList link2 = null;

		HashMap attribute3 = null;

		ArrayList location4 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "headers");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(76, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:76:48: ( ( category | link |
				// attribute | location )* )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:77:26: ( category | link |
				// attribute | location )*
				{
					dbg.location(77, 26);

					value = new HashMap();
					ArrayList catList = new ArrayList();
					ArrayList linkList = new ArrayList();
					ArrayList attrList = new ArrayList();
					ArrayList locList = new ArrayList();

					dbg.location(84, 26);
					// /home/smsnheck/Desktop/Occi.g:84:26: ( category | link |
					// attribute | location )*
					try {
						dbg.enterSubRule(1);

						loop1: do {
							int alt1 = 5;
							try {
								dbg.enterDecision(1, decisionCanBacktrack[1]);

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

							} finally {
								dbg.exitDecision(1);
							}

							switch (alt1) {
							case 1:
								dbg.enterAlt(1);

								// /home/smsnheck/Desktop/Occi.g:85:28: category
								{
									dbg.location(85, 28);
									pushFollow(FOLLOW_category_in_headers149);
									category1 = category();

									state._fsp--;

									dbg.location(85, 38);
									if (category1 != null) {
										catList.add(category1);
									}

								}
								break;
							case 2:
								dbg.enterAlt(2);

								// /home/smsnheck/Desktop/Occi.g:86:28: link
								{
									dbg.location(86, 28);
									pushFollow(FOLLOW_link_in_headers183);
									link2 = link();

									state._fsp--;

									dbg.location(86, 38);
									if (link2 != null) {
										linkList.add(link2);
									}

								}
								break;
							case 3:
								dbg.enterAlt(3);

								// /home/smsnheck/Desktop/Occi.g:87:28:
								// attribute
								{
									dbg.location(87, 28);
									pushFollow(FOLLOW_attribute_in_headers221);
									attribute3 = attribute();

									state._fsp--;

									dbg.location(87, 38);
									if (attribute3 != null) {
										attrList.add(attribute3);
									}

								}
								break;
							case 4:
								dbg.enterAlt(4);

								// /home/smsnheck/Desktop/Occi.g:88:28: location
								{
									dbg.location(88, 28);
									pushFollow(FOLLOW_location_in_headers254);
									location4 = location();

									state._fsp--;

									dbg.location(88, 38);
									if (location4 != null) {
										locList.add(location4);
									}

								}
								break;

							default:
								break loop1;
							}
						} while (true);
					} finally {
						dbg.exitSubRule(1);
					}

					dbg.location(90, 26);

					value.put(occi_categories, catList);
					value.put(occi_links, linkList);
					value.put(occi_attributes, attrList);
					value.put(occi_locations, locList);
					// value = allheaders;

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(97, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "headers");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "headers"

	// $ANTLR start "category"
	// /home/smsnheck/Desktop/Occi.g:111:1: category returns [ArrayList cats] :
	// 'Category' ':' category_values ;
	public final ArrayList category() throws RecognitionException,
			OcciParserException {
		ArrayList cats = null;

		ArrayList category_values5 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "category");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(111, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:111:49: ( 'Category' ':'
				// category_values )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:112:26: 'Category' ':'
				// category_values
				{
					dbg.location(112, 26);
					match(input, 12, FOLLOW_12_in_category392);
					dbg.location(112, 37);
					match(input, 13, FOLLOW_13_in_category394);
					dbg.location(113, 26);
					pushFollow(FOLLOW_category_values_in_category421);
					category_values5 = category_values();

					state._fsp--;

					dbg.location(113, 41);

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
			dbg.location(116, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "category");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return cats;
	}

	// $ANTLR end "category"

	// $ANTLR start "category_values"
	// /home/smsnheck/Desktop/Occi.g:118:1: category_values returns [ArrayList
	// cats] : cv1= category_value ( ',' cv2= category_value )* ;
	public final ArrayList category_values() throws RecognitionException,
			OcciParserException {
		ArrayList cats = null;

		HashMap cv1 = null;

		HashMap cv2 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "category_values");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(118, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:118:49: (cv1= category_value (
				// ',' cv2= category_value )* )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:119:25: cv1= category_value (
				// ',' cv2= category_value )*
				{
					dbg.location(119, 28);
					pushFollow(FOLLOW_category_value_in_category_values493);
					cv1 = category_value();

					state._fsp--;

					dbg.location(119, 43);

					cats = new ArrayList();
					cats.add(cv1);

					dbg.location(123, 25);
					// /home/smsnheck/Desktop/Occi.g:123:25: ( ',' cv2=
					// category_value )*
					try {
						dbg.enterSubRule(2);

						loop2: do {
							int alt2 = 2;
							try {
								dbg.enterDecision(2, decisionCanBacktrack[2]);

								int LA2_0 = input.LA(1);

								if ((LA2_0 == 14)) {
									alt2 = 1;
								}

							} finally {
								dbg.exitDecision(2);
							}

							switch (alt2) {
							case 1:
								dbg.enterAlt(1);

								// /home/smsnheck/Desktop/Occi.g:124:25: ','
								// cv2= category_value
								{
									dbg.location(124, 25);
									match(input, 14,
											FOLLOW_14_in_category_values546);
									dbg.location(124, 32);
									pushFollow(FOLLOW_category_value_in_category_values550);
									cv2 = category_value();

									state._fsp--;

									dbg.location(124, 47);

									cats.add(cv2);

								}
								break;

							default:
								break loop2;
							}
						} while (true);
					} finally {
						dbg.exitSubRule(2);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(128, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "category_values");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return cats;
	}

	// $ANTLR end "category_values"

	// $ANTLR start "category_value"
	// /home/smsnheck/Desktop/Occi.g:130:1: category_value returns [HashMap cat]
	// : term_attr scheme_attr klass_attr ( title_attr )? ( rel_attr )? (
	// location_attr )? ( c_attributes_attr )? ( actions_attr )? ;
	public final HashMap category_value() throws RecognitionException,
			OcciParserException {
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
			dbg.enterRule(getGrammarFileName(), "category_value");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(130, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:130:46: ( term_attr scheme_attr
				// klass_attr ( title_attr )? ( rel_attr )? ( location_attr )? (
				// c_attributes_attr )? ( actions_attr )? )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:131:25: term_attr scheme_attr
				// klass_attr ( title_attr )? ( rel_attr )? ( location_attr )? (
				// c_attributes_attr )? ( actions_attr )?
				{
					dbg.location(131, 25);
					pushFollow(FOLLOW_term_attr_in_category_value647);
					term_attr6 = term_attr();

					state._fsp--;

					dbg.location(131, 35);
					pushFollow(FOLLOW_scheme_attr_in_category_value649);
					scheme_attr7 = scheme_attr();

					state._fsp--;

					dbg.location(131, 47);
					pushFollow(FOLLOW_klass_attr_in_category_value651);
					klass_attr8 = klass_attr();

					state._fsp--;

					dbg.location(131, 58);
					// /home/smsnheck/Desktop/Occi.g:131:58: ( title_attr )?
					int alt3 = 2;
					try {
						dbg.enterSubRule(3);
						try {
							dbg.enterDecision(3, decisionCanBacktrack[3]);

							int LA3_0 = input.LA(1);

							if ((LA3_0 == 15)) {
								int LA3_1 = input.LA(2);

								if ((LA3_1 == 19)) {
									alt3 = 1;
								}
							}
						} finally {
							dbg.exitDecision(3);
						}

						switch (alt3) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:131:58: title_attr
							{
								dbg.location(131, 58);
								pushFollow(FOLLOW_title_attr_in_category_value653);
								title_attr9 = title_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(3);
					}

					dbg.location(131, 70);
					// /home/smsnheck/Desktop/Occi.g:131:70: ( rel_attr )?
					int alt4 = 2;
					try {
						dbg.enterSubRule(4);
						try {
							dbg.enterDecision(4, decisionCanBacktrack[4]);

							int LA4_0 = input.LA(1);

							if ((LA4_0 == 15)) {
								int LA4_1 = input.LA(2);

								if ((LA4_1 == 20)) {
									alt4 = 1;
								}
							}
						} finally {
							dbg.exitDecision(4);
						}

						switch (alt4) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:131:70: rel_attr
							{
								dbg.location(131, 70);
								pushFollow(FOLLOW_rel_attr_in_category_value656);
								rel_attr10 = rel_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(4);
					}

					dbg.location(131, 80);
					// /home/smsnheck/Desktop/Occi.g:131:80: ( location_attr )?
					int alt5 = 2;
					try {
						dbg.enterSubRule(5);
						try {
							dbg.enterDecision(5, decisionCanBacktrack[5]);

							int LA5_0 = input.LA(1);

							if ((LA5_0 == 15)) {
								int LA5_1 = input.LA(2);

								if ((LA5_1 == 21)) {
									alt5 = 1;
								}
							}
						} finally {
							dbg.exitDecision(5);
						}

						switch (alt5) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:131:80:
							// location_attr
							{
								dbg.location(131, 80);
								pushFollow(FOLLOW_location_attr_in_category_value659);
								location_attr11 = location_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(5);
					}

					dbg.location(131, 95);
					// /home/smsnheck/Desktop/Occi.g:131:95: ( c_attributes_attr
					// )?
					int alt6 = 2;
					try {
						dbg.enterSubRule(6);
						try {
							dbg.enterDecision(6, decisionCanBacktrack[6]);

							int LA6_0 = input.LA(1);

							if ((LA6_0 == 15)) {
								int LA6_1 = input.LA(2);

								if ((LA6_1 == 22)) {
									alt6 = 1;
								}
							}
						} finally {
							dbg.exitDecision(6);
						}

						switch (alt6) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:131:95:
							// c_attributes_attr
							{
								dbg.location(131, 95);
								pushFollow(FOLLOW_c_attributes_attr_in_category_value662);
								c_attributes_attr12 = c_attributes_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(6);
					}

					dbg.location(131, 114);
					// /home/smsnheck/Desktop/Occi.g:131:114: ( actions_attr )?
					int alt7 = 2;
					try {
						dbg.enterSubRule(7);
						try {
							dbg.enterDecision(7, decisionCanBacktrack[7]);

							int LA7_0 = input.LA(1);

							if ((LA7_0 == 15)) {
								alt7 = 1;
							}
						} finally {
							dbg.exitDecision(7);
						}

						switch (alt7) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:131:114:
							// actions_attr
							{
								dbg.location(131, 114);
								pushFollow(FOLLOW_actions_attr_in_category_value665);
								actions_attr13 = actions_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(7);
					}

					dbg.location(131, 127);

					cat = new HashMap();

					cat.put(occi_core_term, term_attr6);
					cat.put(occi_core_scheme, scheme_attr7);
					cat.put(occi_core_class, klass_attr8);

					if (title_attr9 != null) {
						cat.put(occi_core_title, title_attr9);
					}

					if (rel_attr10 != null) {
						cat.put(occi_core_rel, rel_attr10);
					}

					if (location_attr11 != null) {
						cat.put(occi_core_location, location_attr11);
					}

					if (c_attributes_attr12 != null) {
						cat.put(occi_core_attributes, c_attributes_attr12);
					}

					if (actions_attr13 != null) {
						cat.put(occi_core_actions, actions_attr13);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(153, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "category_value");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return cat;
	}

	// $ANTLR end "category_value"

	// $ANTLR start "term_attr"
	// /home/smsnheck/Desktop/Occi.g:155:1: term_attr returns [String value] :
	// TERM_VALUE ;
	public final String term_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token TERM_VALUE14 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "term_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(155, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:155:47: ( TERM_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:156:25: TERM_VALUE
				{
					dbg.location(156, 25);
					TERM_VALUE14 = (Token) match(input, TERM_VALUE,
							FOLLOW_TERM_VALUE_in_term_attr742);
					dbg.location(156, 35);

					value = (TERM_VALUE14 != null ? TERM_VALUE14.getText()
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
			dbg.location(159, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "term_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "term_attr"

	// $ANTLR start "scheme_attr"
	// /home/smsnheck/Desktop/Occi.g:162:1: scheme_attr returns [String value] :
	// ';' 'scheme' '=' QUOTED_VALUE ;
	public final String scheme_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE15 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "scheme_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(162, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:162:47: ( ';' 'scheme' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:163:25: ';' 'scheme' '='
				// QUOTED_VALUE
				{
					dbg.location(163, 25);
					match(input, 15, FOLLOW_15_in_scheme_attr816);
					dbg.location(163, 29);
					match(input, 16, FOLLOW_16_in_scheme_attr818);
					dbg.location(163, 38);
					match(input, 17, FOLLOW_17_in_scheme_attr820);
					dbg.location(164, 25);
					QUOTED_VALUE15 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_scheme_attr846);
					dbg.location(164, 37);

					value = removeQuotes((QUOTED_VALUE15 != null ? QUOTED_VALUE15
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(167, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "scheme_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "scheme_attr"

	// $ANTLR start "klass_attr"
	// /home/smsnheck/Desktop/Occi.g:169:1: klass_attr returns [String value] :
	// ';' 'class' '=' QUOTED_VALUE ;
	public final String klass_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE16 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "klass_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(169, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:169:47: ( ';' 'class' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:170:25: ';' 'class' '='
				// QUOTED_VALUE
				{
					dbg.location(170, 25);
					match(input, 15, FOLLOW_15_in_klass_attr920);
					dbg.location(170, 29);
					match(input, 18, FOLLOW_18_in_klass_attr922);
					dbg.location(170, 37);
					match(input, 17, FOLLOW_17_in_klass_attr924);
					dbg.location(171, 25);
					QUOTED_VALUE16 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_klass_attr950);
					dbg.location(171, 37);

					String klass = removeQuotes((QUOTED_VALUE16 != null ? QUOTED_VALUE16
							.getText()
							: null));

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
			dbg.location(182, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "klass_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "klass_attr"

	// $ANTLR start "title_attr"
	// /home/smsnheck/Desktop/Occi.g:184:1: title_attr returns [String value] :
	// ';' 'title' '=' QUOTED_VALUE ;
	public final String title_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE17 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "title_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(184, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:184:47: ( ';' 'title' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:185:25: ';' 'title' '='
				// QUOTED_VALUE
				{
					dbg.location(185, 25);
					match(input, 15, FOLLOW_15_in_title_attr1024);
					dbg.location(185, 29);
					match(input, 19, FOLLOW_19_in_title_attr1026);
					dbg.location(185, 37);
					match(input, 17, FOLLOW_17_in_title_attr1028);
					dbg.location(186, 25);
					QUOTED_VALUE17 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_title_attr1054);
					dbg.location(186, 37);

					value = removeQuotes((QUOTED_VALUE17 != null ? QUOTED_VALUE17
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(189, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "title_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "title_attr"

	// $ANTLR start "rel_attr"
	// /home/smsnheck/Desktop/Occi.g:192:1: rel_attr returns [String value] :
	// ';' 'rel' '=' QUOTED_VALUE ;
	public final String rel_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE18 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "rel_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(192, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:192:47: ( ';' 'rel' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:193:25: ';' 'rel' '='
				// QUOTED_VALUE
				{
					dbg.location(193, 25);
					match(input, 15, FOLLOW_15_in_rel_attr1131);
					dbg.location(193, 29);
					match(input, 20, FOLLOW_20_in_rel_attr1133);
					dbg.location(193, 35);
					match(input, 17, FOLLOW_17_in_rel_attr1135);
					dbg.location(194, 25);
					QUOTED_VALUE18 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_rel_attr1161);
					dbg.location(194, 37);

					value = removeQuotes((QUOTED_VALUE18 != null ? QUOTED_VALUE18
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(197, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "rel_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "rel_attr"

	// $ANTLR start "location_attr"
	// /home/smsnheck/Desktop/Occi.g:201:1: location_attr returns [String value]
	// : ';' 'location' '=' TARGET_VALUE ;
	public final String location_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token TARGET_VALUE19 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "location_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(201, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:201:47: ( ';' 'location' '='
				// TARGET_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:202:25: ';' 'location' '='
				// TARGET_VALUE
				{
					dbg.location(202, 25);
					match(input, 15, FOLLOW_15_in_location_attr1234);
					dbg.location(202, 29);
					match(input, 21, FOLLOW_21_in_location_attr1236);
					dbg.location(202, 40);
					match(input, 17, FOLLOW_17_in_location_attr1238);
					dbg.location(203, 25);
					TARGET_VALUE19 = (Token) match(input, TARGET_VALUE,
							FOLLOW_TARGET_VALUE_in_location_attr1264);
					dbg.location(203, 37);

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
			dbg.location(206, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "location_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "location_attr"

	// $ANTLR start "c_attributes_attr"
	// /home/smsnheck/Desktop/Occi.g:209:1: c_attributes_attr returns [String
	// value] : ';' 'attributes' '=' QUOTED_VALUE ;
	public final String c_attributes_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE20 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "c_attributes_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(209, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:209:47: ( ';' 'attributes' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:210:25: ';' 'attributes' '='
				// QUOTED_VALUE
				{
					dbg.location(210, 25);
					match(input, 15, FOLLOW_15_in_c_attributes_attr1332);
					dbg.location(210, 29);
					match(input, 22, FOLLOW_22_in_c_attributes_attr1334);
					dbg.location(210, 42);
					match(input, 17, FOLLOW_17_in_c_attributes_attr1336);
					dbg.location(211, 25);
					QUOTED_VALUE20 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_c_attributes_attr1362);
					dbg.location(211, 37);

					value = removeQuotes((QUOTED_VALUE20 != null ? QUOTED_VALUE20
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(214, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "c_attributes_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "c_attributes_attr"

	// $ANTLR start "actions_attr"
	// /home/smsnheck/Desktop/Occi.g:217:1: actions_attr returns [String value]
	// : ';' 'actions' '=' QUOTED_VALUE ;
	public final String actions_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE21 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "actions_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(217, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:217:47: ( ';' 'actions' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:218:25: ';' 'actions' '='
				// QUOTED_VALUE
				{
					dbg.location(218, 25);
					match(input, 15, FOLLOW_15_in_actions_attr1435);
					dbg.location(218, 29);
					match(input, 23, FOLLOW_23_in_actions_attr1437);
					dbg.location(218, 39);
					match(input, 17, FOLLOW_17_in_actions_attr1439);
					dbg.location(219, 25);
					QUOTED_VALUE21 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_actions_attr1465);
					dbg.location(219, 37);

					value = removeQuotes((QUOTED_VALUE21 != null ? QUOTED_VALUE21
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(222, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "actions_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "actions_attr"

	// $ANTLR start "link"
	// /home/smsnheck/Desktop/Occi.g:233:1: link returns [ArrayList link] :
	// 'Link' ':' link_values ;
	public final ArrayList link() throws RecognitionException,
			OcciParserException {
		ArrayList link = null;

		ArrayList link_values22 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "link");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(233, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:233:49: ( 'Link' ':'
				// link_values )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:234:26: 'Link' ':' link_values
				{
					dbg.location(234, 26);
					match(input, 24, FOLLOW_24_in_link1549);
					dbg.location(234, 33);
					match(input, 13, FOLLOW_13_in_link1551);
					dbg.location(235, 27);
					pushFollow(FOLLOW_link_values_in_link1579);
					link_values22 = link_values();

					state._fsp--;

					dbg.location(235, 39);

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
			dbg.location(238, 27);

		} finally {
			dbg.exitRule(getGrammarFileName(), "link");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return link;
	}

	// $ANTLR end "link"

	// $ANTLR start "link_values"
	// /home/smsnheck/Desktop/Occi.g:240:1: link_values returns [ArrayList
	// links] : lv1= link_value ( ',' lv2= link_value )* ;
	public final ArrayList link_values() throws RecognitionException,
			OcciParserException {
		ArrayList links = null;

		HashMap lv1 = null;

		HashMap lv2 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "link_values");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(240, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:240:50: (lv1= link_value ( ','
				// lv2= link_value )* )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:241:26: lv1= link_value ( ','
				// lv2= link_value )*
				{
					dbg.location(241, 29);
					pushFollow(FOLLOW_link_value_in_link_values1658);
					lv1 = link_value();

					state._fsp--;

					dbg.location(241, 41);

					links = new ArrayList();
					links.add(lv1);

					dbg.location(245, 26);
					// /home/smsnheck/Desktop/Occi.g:245:26: ( ',' lv2=
					// link_value )*
					try {
						dbg.enterSubRule(8);

						loop8: do {
							int alt8 = 2;
							try {
								dbg.enterDecision(8, decisionCanBacktrack[8]);

								int LA8_0 = input.LA(1);

								if ((LA8_0 == 14)) {
									alt8 = 1;
								}

							} finally {
								dbg.exitDecision(8);
							}

							switch (alt8) {
							case 1:
								dbg.enterAlt(1);

								// /home/smsnheck/Desktop/Occi.g:246:28: ','
								// lv2= link_value
								{
									dbg.location(246, 28);
									match(input, 14,
											FOLLOW_14_in_link_values1716);
									dbg.location(246, 35);
									pushFollow(FOLLOW_link_value_in_link_values1720);
									lv2 = link_value();

									state._fsp--;

									dbg.location(246, 46);

									links.add(lv2);

								}
								break;

							default:
								break loop8;
							}
						} while (true);
					} finally {
						dbg.exitSubRule(8);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(250, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "link_values");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return links;
	}

	// $ANTLR end "link_values"

	// $ANTLR start "link_value"
	// /home/smsnheck/Desktop/Occi.g:252:1: link_value returns [HashMap
	// linkAttrs] : target_attr rel_attr ( self_attr )? ( category_attr )? (
	// attribute_attr )? ;
	public final HashMap link_value() throws RecognitionException,
			OcciParserException {
		HashMap linkAttrs = null;

		ArrayList target_attr23 = null;

		String rel_attr24 = null;

		String self_attr25 = null;

		String category_attr26 = null;

		HashMap attribute_attr27 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "link_value");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(252, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:252:52: ( target_attr rel_attr
				// ( self_attr )? ( category_attr )? ( attribute_attr )? )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:253:26: target_attr rel_attr (
				// self_attr )? ( category_attr )? ( attribute_attr )?
				{
					dbg.location(253, 26);
					pushFollow(FOLLOW_target_attr_in_link_value1824);
					target_attr23 = target_attr();

					state._fsp--;

					dbg.location(253, 38);
					pushFollow(FOLLOW_rel_attr_in_link_value1826);
					rel_attr24 = rel_attr();

					state._fsp--;

					dbg.location(253, 47);
					// /home/smsnheck/Desktop/Occi.g:253:47: ( self_attr )?
					int alt9 = 2;
					try {
						dbg.enterSubRule(9);
						try {
							dbg.enterDecision(9, decisionCanBacktrack[9]);

							int LA9_0 = input.LA(1);

							if ((LA9_0 == 15)) {
								int LA9_1 = input.LA(2);

								if ((LA9_1 == 28)) {
									alt9 = 1;
								}
							}
						} finally {
							dbg.exitDecision(9);
						}

						switch (alt9) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:253:47: self_attr
							{
								dbg.location(253, 47);
								pushFollow(FOLLOW_self_attr_in_link_value1828);
								self_attr25 = self_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(9);
					}

					dbg.location(253, 58);
					// /home/smsnheck/Desktop/Occi.g:253:58: ( category_attr )?
					int alt10 = 2;
					try {
						dbg.enterSubRule(10);
						try {
							dbg.enterDecision(10, decisionCanBacktrack[10]);

							int LA10_0 = input.LA(1);

							if ((LA10_0 == 15)) {
								int LA10_1 = input.LA(2);

								if ((LA10_1 == 29)) {
									alt10 = 1;
								}
							}
						} finally {
							dbg.exitDecision(10);
						}

						switch (alt10) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:253:58:
							// category_attr
							{
								dbg.location(253, 58);
								pushFollow(FOLLOW_category_attr_in_link_value1831);
								category_attr26 = category_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(10);
					}

					dbg.location(253, 73);
					// /home/smsnheck/Desktop/Occi.g:253:73: ( attribute_attr )?
					int alt11 = 2;
					try {
						dbg.enterSubRule(11);
						try {
							dbg.enterDecision(11, decisionCanBacktrack[11]);

							int LA11_0 = input.LA(1);

							if ((LA11_0 == 15)) {
								alt11 = 1;
							}
						} finally {
							dbg.exitDecision(11);
						}

						switch (alt11) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:253:73:
							// attribute_attr
							{
								dbg.location(253, 73);
								pushFollow(FOLLOW_attribute_attr_in_link_value1834);
								attribute_attr27 = attribute_attr();

								state._fsp--;

							}
							break;

						}
					} finally {
						dbg.exitSubRule(11);
					}

					dbg.location(254, 26);

					linkAttrs = new HashMap();

					linkAttrs.put(occi_core_target, target_attr23.get(0));
					if (target_attr23.size() == 2) {
						linkAttrs.put(occi_core_actionterm, target_attr23
								.get(1));
					}

					linkAttrs.put(occi_core_rel, rel_attr24);

					if (self_attr25 != null) {
						linkAttrs.put(occi_core_self, self_attr25);
					}

					if (category_attr26 != null) {
						linkAttrs.put(occi_core_category, category_attr26);
					}

					if (attribute_attr27 != null) {
						linkAttrs.putAll(attribute_attr27);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(272, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "link_value");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return linkAttrs;
	}

	// $ANTLR end "link_value"

	// $ANTLR start "target_attr"
	// /home/smsnheck/Desktop/Occi.g:274:1: target_attr returns [ArrayList
	// targetAndTerm] : '<' TARGET_VALUE ( '?action=' TERM_VALUE )? '>' ;
	public final ArrayList target_attr() throws RecognitionException,
			OcciParserException {
		ArrayList targetAndTerm = null;

		Token TARGET_VALUE28 = null;
		Token TERM_VALUE29 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "target_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(274, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:274:58: ( '<' TARGET_VALUE (
				// '?action=' TERM_VALUE )? '>' )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:275:26: '<' TARGET_VALUE (
				// '?action=' TERM_VALUE )? '>'
				{
					dbg.location(275, 26);
					match(input, 25, FOLLOW_25_in_target_attr1936);
					dbg.location(275, 30);
					TARGET_VALUE28 = (Token) match(input, TARGET_VALUE,
							FOLLOW_TARGET_VALUE_in_target_attr1938);
					dbg.location(275, 43);

					targetAndTerm = new ArrayList();
					targetAndTerm.add((TARGET_VALUE28 != null ? TARGET_VALUE28
							.getText() : null));

					dbg.location(279, 26);
					// /home/smsnheck/Desktop/Occi.g:279:26: ( '?action='
					// TERM_VALUE )?
					int alt12 = 2;
					try {
						dbg.enterSubRule(12);
						try {
							dbg.enterDecision(12, decisionCanBacktrack[12]);

							int LA12_0 = input.LA(1);

							if ((LA12_0 == 26)) {
								alt12 = 1;
							}
						} finally {
							dbg.exitDecision(12);
						}

						switch (alt12) {
						case 1:
							dbg.enterAlt(1);

							// /home/smsnheck/Desktop/Occi.g:279:27: '?action='
							// TERM_VALUE
							{
								dbg.location(279, 27);
								match(input, 26, FOLLOW_26_in_target_attr1968);
								dbg.location(280, 28);
								TERM_VALUE29 = (Token) match(input, TERM_VALUE,
										FOLLOW_TERM_VALUE_in_target_attr1997);
								dbg.location(280, 38);

								targetAndTerm
										.add((TERM_VALUE29 != null ? TERM_VALUE29
												.getText()
												: null));

							}
							break;

						}
					} finally {
						dbg.exitSubRule(12);
					}

					dbg.location(283, 29);
					match(input, 27, FOLLOW_27_in_target_attr2028);

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(284, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "target_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return targetAndTerm;
	}

	// $ANTLR end "target_attr"

	// $ANTLR start "self_attr"
	// /home/smsnheck/Desktop/Occi.g:286:1: self_attr returns [String value] :
	// ';' 'self' '=' QUOTED_VALUE ;
	public final String self_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE30 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "self_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(286, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:286:47: ( ';' 'self' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:287:26: ';' 'self' '='
				// QUOTED_VALUE
				{
					dbg.location(287, 26);
					match(input, 15, FOLLOW_15_in_self_attr2104);
					dbg.location(287, 30);
					match(input, 28, FOLLOW_28_in_self_attr2106);
					dbg.location(287, 37);
					match(input, 17, FOLLOW_17_in_self_attr2108);
					dbg.location(288, 26);
					QUOTED_VALUE30 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_self_attr2135);
					dbg.location(288, 38);

					value = removeQuotes((QUOTED_VALUE30 != null ? QUOTED_VALUE30
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(291, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "self_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "self_attr"

	// $ANTLR start "category_attr"
	// /home/smsnheck/Desktop/Occi.g:293:1: category_attr returns [String value]
	// : ';' 'category' '=' QUOTED_VALUE ;
	public final String category_attr() throws RecognitionException,
			OcciParserException {
		String value = null;

		Token QUOTED_VALUE31 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "category_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(293, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:293:47: ( ';' 'category' '='
				// QUOTED_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:294:26: ';' 'category' '='
				// QUOTED_VALUE
				{
					dbg.location(294, 26);
					match(input, 15, FOLLOW_15_in_category_attr2208);
					dbg.location(294, 30);
					match(input, 29, FOLLOW_29_in_category_attr2210);
					dbg.location(294, 41);
					match(input, 17, FOLLOW_17_in_category_attr2212);
					dbg.location(295, 26);
					QUOTED_VALUE31 = (Token) match(input, QUOTED_VALUE,
							FOLLOW_QUOTED_VALUE_in_category_attr2239);
					dbg.location(295, 39);

					value = removeQuotes((QUOTED_VALUE31 != null ? QUOTED_VALUE31
							.getText()
							: null));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(298, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "category_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return value;
	}

	// $ANTLR end "category_attr"

	// $ANTLR start "attribute_attr"
	// /home/smsnheck/Desktop/Occi.g:300:1: attribute_attr returns [HashMap
	// attr] : ';' attributes_attr ;
	public final HashMap attribute_attr() throws RecognitionException,
			OcciParserException {
		HashMap attr = null;

		HashMap attributes_attr32 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "attribute_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(300, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:300:47: ( ';' attributes_attr )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:301:25: ';' attributes_attr
				{
					dbg.location(301, 25);
					match(input, 15, FOLLOW_15_in_attribute_attr2311);
					dbg.location(301, 29);
					pushFollow(FOLLOW_attributes_attr_in_attribute_attr2313);
					attributes_attr32 = attributes_attr();

					state._fsp--;

					dbg.location(301, 45);

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
			dbg.location(304, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attribute_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return attr;
	}

	// $ANTLR end "attribute_attr"

	// $ANTLR start "attributes_attr"
	// /home/smsnheck/Desktop/Occi.g:306:1: attributes_attr returns [HashMap
	// attrs] : kv1= attribute_kv_attr ( ',' kv2= attribute_kv_attr )* ;
	public final HashMap attributes_attr() throws RecognitionException,
			OcciParserException {
		HashMap attrs = null;

		ArrayList kv1 = null;

		ArrayList kv2 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "attributes_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(306, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:306:48: (kv1= attribute_kv_attr
				// ( ',' kv2= attribute_kv_attr )* )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:307:26: kv1= attribute_kv_attr
				// ( ',' kv2= attribute_kv_attr )*
				{
					dbg.location(307, 29);
					pushFollow(FOLLOW_attribute_kv_attr_in_attributes_attr2386);
					kv1 = attribute_kv_attr();

					state._fsp--;

					dbg.location(307, 48);

					attrs = new HashMap();
					attrs.put(kv1.get(0), kv1.get(1));

					dbg.location(311, 26);
					// /home/smsnheck/Desktop/Occi.g:311:26: ( ',' kv2=
					// attribute_kv_attr )*
					try {
						dbg.enterSubRule(13);

						loop13: do {
							int alt13 = 2;
							try {
								dbg.enterDecision(13, decisionCanBacktrack[13]);

								int LA13_0 = input.LA(1);

								if ((LA13_0 == 14)) {
									int LA13_1 = input.LA(2);

									if ((LA13_1 == TERM_VALUE)) {
										alt13 = 1;
									}

								}

							} finally {
								dbg.exitDecision(13);
							}

							switch (alt13) {
							case 1:
								dbg.enterAlt(1);

								// /home/smsnheck/Desktop/Occi.g:312:28: ','
								// kv2= attribute_kv_attr
								{
									dbg.location(312, 28);
									match(input, 14,
											FOLLOW_14_in_attributes_attr2444);
									dbg.location(312, 35);
									pushFollow(FOLLOW_attribute_kv_attr_in_attributes_attr2448);
									kv2 = attribute_kv_attr();

									state._fsp--;

									dbg.location(312, 54);

									attrs.put(kv2.get(0), kv2.get(1));

								}
								break;

							default:
								break loop13;
							}
						} while (true);
					} finally {
						dbg.exitSubRule(13);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(316, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attributes_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return attrs;
	}

	// $ANTLR end "attributes_attr"

	// $ANTLR start "attribute_kv_attr"
	// /home/smsnheck/Desktop/Occi.g:318:1: attribute_kv_attr returns [ArrayList
	// keyval] : attribute_name_attr '=' attribute_value_attr ;
	public final ArrayList attribute_kv_attr() throws RecognitionException,
			OcciParserException {
		ArrayList keyval = null;

		OcciParser.attribute_name_attr_return attribute_name_attr33 = null;

		OcciParser.attribute_value_attr_return attribute_value_attr34 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "attribute_kv_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(318, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:318:51: ( attribute_name_attr
				// '=' attribute_value_attr )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:319:26: attribute_name_attr '='
				// attribute_value_attr
				{
					dbg.location(319, 26);
					pushFollow(FOLLOW_attribute_name_attr_in_attribute_kv_attr2546);
					attribute_name_attr33 = attribute_name_attr();

					state._fsp--;

					dbg.location(319, 46);
					match(input, 17, FOLLOW_17_in_attribute_kv_attr2548);
					dbg.location(319, 50);
					pushFollow(FOLLOW_attribute_value_attr_in_attribute_kv_attr2550);
					attribute_value_attr34 = attribute_value_attr();

					state._fsp--;

					dbg.location(319, 71);

					keyval = new ArrayList();
					keyval.add((attribute_name_attr33 != null ? input.toString(
							attribute_name_attr33.start,
							attribute_name_attr33.stop) : null));
					keyval
							.add(removeQuotes((attribute_value_attr34 != null ? input
									.toString(attribute_value_attr34.start,
											attribute_value_attr34.stop)
									: null)));

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(324, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attribute_kv_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return keyval;
	}

	// $ANTLR end "attribute_kv_attr"

	public static class attribute_name_attr_return extends
			ParserRuleReturnScope {
	};

	// $ANTLR start "attribute_name_attr"
	// /home/smsnheck/Desktop/Occi.g:327:1: attribute_name_attr : TERM_VALUE ;
	public final OcciParser.attribute_name_attr_return attribute_name_attr()
			throws RecognitionException, OcciParserException {
		OcciParser.attribute_name_attr_return retval = new OcciParser.attribute_name_attr_return();
		retval.start = input.LT(1);

		try {
			dbg.enterRule(getGrammarFileName(), "attribute_name_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(327, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:327:24: ( TERM_VALUE )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:327:26: TERM_VALUE
				{
					dbg.location(327, 26);
					match(input, TERM_VALUE,
							FOLLOW_TERM_VALUE_in_attribute_name_attr2590);

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
			dbg.location(327, 36);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attribute_name_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return retval;
	}

	// $ANTLR end "attribute_name_attr"

	public static class attribute_value_attr_return extends
			ParserRuleReturnScope {
	};

	// $ANTLR start "attribute_value_attr"
	// /home/smsnheck/Desktop/Occi.g:328:1: attribute_value_attr : (
	// QUOTED_VALUE | DIGITS | FLOAT );
	public final OcciParser.attribute_value_attr_return attribute_value_attr()
			throws RecognitionException, OcciParserException {
		OcciParser.attribute_value_attr_return retval = new OcciParser.attribute_value_attr_return();
		retval.start = input.LT(1);

		try {
			dbg.enterRule(getGrammarFileName(), "attribute_value_attr");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(328, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:328:24: ( QUOTED_VALUE | DIGITS
				// | FLOAT )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:
				{
					dbg.location(328, 24);
					if (input.LA(1) == QUOTED_VALUE
							|| (input.LA(1) >= DIGITS && input.LA(1) <= FLOAT)) {
						input.consume();
						state.errorRecovery = false;
					} else {
						MismatchedSetException mse = new MismatchedSetException(
								null, input);
						dbg.recognitionException(mse);
						throw mse;
					}

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
			dbg.location(328, 56);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attribute_value_attr");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return retval;
	}

	// $ANTLR end "attribute_value_attr"

	// $ANTLR start "attribute"
	// /home/smsnheck/Desktop/Occi.g:340:1: attribute returns [HashMap attrs] :
	// 'X-OCCI-Attribute' ':' attributes_attr ;
	public final HashMap attribute() throws RecognitionException,
			OcciParserException {
		HashMap attrs = null;

		HashMap attributes_attr35 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "attribute");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(340, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:340:48: ( 'X-OCCI-Attribute'
				// ':' attributes_attr )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:341:26: 'X-OCCI-Attribute' ':'
				// attributes_attr
				{
					dbg.location(341, 26);
					match(input, 30, FOLLOW_30_in_attribute2660);
					dbg.location(341, 45);
					match(input, 13, FOLLOW_13_in_attribute2662);
					dbg.location(342, 26);
					pushFollow(FOLLOW_attributes_attr_in_attribute2689);
					attributes_attr35 = attributes_attr();

					state._fsp--;

					dbg.location(342, 41);

					attrs = attributes_attr35;

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(345, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "attribute");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return attrs;
	}

	// $ANTLR end "attribute"

	// $ANTLR start "location"
	// /home/smsnheck/Desktop/Occi.g:352:1: location returns [ArrayList urls] :
	// 'X-OCCI-Location' ':' location_values ;
	public final ArrayList location() throws RecognitionException,
			OcciParserException {
		ArrayList urls = null;

		ArrayList location_values36 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "location");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(352, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:352:49: ( 'X-OCCI-Location' ':'
				// location_values )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:353:26: 'X-OCCI-Location' ':'
				// location_values
				{
					dbg.location(353, 26);
					match(input, 31, FOLLOW_31_in_location2768);
					dbg.location(353, 44);
					match(input, 13, FOLLOW_13_in_location2770);
					dbg.location(354, 26);
					pushFollow(FOLLOW_location_values_in_location2797);
					location_values36 = location_values();

					state._fsp--;

					dbg.location(354, 41);

					urls = location_values36;

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(357, 26);

		} finally {
			dbg.exitRule(getGrammarFileName(), "location");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return urls;
	}

	// $ANTLR end "location"

	// $ANTLR start "location_values"
	// /home/smsnheck/Desktop/Occi.g:359:1: location_values returns [ArrayList
	// urls] : u1= URL ( ',' u2= URL )* ;
	public final ArrayList location_values() throws RecognitionException,
			OcciParserException {
		ArrayList urls = null;

		Token u1 = null;
		Token u2 = null;

		try {
			dbg.enterRule(getGrammarFileName(), "location_values");
			if (getRuleLevel() == 0) {
				dbg.commence();
			}
			incRuleLevel();
			dbg.location(359, 1);

			try {
				// /home/smsnheck/Desktop/Occi.g:359:48: (u1= URL ( ',' u2= URL
				// )* )
				dbg.enterAlt(1);

				// /home/smsnheck/Desktop/Occi.g:360:25: u1= URL ( ',' u2= URL
				// )*
				{
					dbg.location(360, 27);
					u1 = (Token) match(input, URL,
							FOLLOW_URL_in_location_values2868);
					dbg.location(360, 32);

					urls = new ArrayList();
					urls.add((u1 != null ? u1.getText() : null));

					dbg.location(364, 25);
					// /home/smsnheck/Desktop/Occi.g:364:25: ( ',' u2= URL )*
					try {
						dbg.enterSubRule(14);

						loop14: do {
							int alt14 = 2;
							try {
								dbg.enterDecision(14, decisionCanBacktrack[14]);

								int LA14_0 = input.LA(1);

								if ((LA14_0 == 14)) {
									alt14 = 1;
								}

							} finally {
								dbg.exitDecision(14);
							}

							switch (alt14) {
							case 1:
								dbg.enterAlt(1);

								// /home/smsnheck/Desktop/Occi.g:365:27: ',' u2=
								// URL
								{
									dbg.location(365, 27);
									match(input, 14,
											FOLLOW_14_in_location_values2924);
									dbg.location(366, 29);
									u2 = (Token) match(input, URL,
											FOLLOW_URL_in_location_values2954);
									dbg.location(366, 33);

									urls
											.add((u2 != null ? u2.getText()
													: null));

								}
								break;

							default:
								break loop14;
							}
						} while (true);
					} finally {
						dbg.exitSubRule(14);
					}

				}

			}

			catch (RecognitionException rex) {

				last_error = getErrorHeader(rex) + " "
						+ getErrorMessage(rex, OcciParser.tokenNames);
				// System.out.println("Parser error: " + last_error);

				throw new OcciParserException(last_error);
			} finally {
			}
			dbg.location(370, 25);

		} finally {
			dbg.exitRule(getGrammarFileName(), "location_values");
			decRuleLevel();
			if (getRuleLevel() == 0) {
				dbg.terminate();
			}
		}

		return urls;
	}

	// $ANTLR end "location_values"

	// Delegated rules

	public static final BitSet FOLLOW_category_in_headers149 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_link_in_headers183 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_attribute_in_headers221 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_location_in_headers254 = new BitSet(
			new long[] { 0x00000000C1001002L });
	public static final BitSet FOLLOW_12_in_category392 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_category394 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_category_values_in_category421 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_category_value_in_category_values493 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_category_values546 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_category_value_in_category_values550 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_term_attr_in_category_value647 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_scheme_attr_in_category_value649 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_klass_attr_in_category_value651 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_title_attr_in_category_value653 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_rel_attr_in_category_value656 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_location_attr_in_category_value659 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_c_attributes_attr_in_category_value662 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_actions_attr_in_category_value665 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TERM_VALUE_in_term_attr742 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_scheme_attr816 = new BitSet(
			new long[] { 0x0000000000010000L });
	public static final BitSet FOLLOW_16_in_scheme_attr818 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_scheme_attr820 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_scheme_attr846 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_klass_attr920 = new BitSet(
			new long[] { 0x0000000000040000L });
	public static final BitSet FOLLOW_18_in_klass_attr922 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_klass_attr924 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_klass_attr950 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_title_attr1024 = new BitSet(
			new long[] { 0x0000000000080000L });
	public static final BitSet FOLLOW_19_in_title_attr1026 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_title_attr1028 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_title_attr1054 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_rel_attr1131 = new BitSet(
			new long[] { 0x0000000000100000L });
	public static final BitSet FOLLOW_20_in_rel_attr1133 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_rel_attr1135 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_rel_attr1161 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_location_attr1234 = new BitSet(
			new long[] { 0x0000000000200000L });
	public static final BitSet FOLLOW_21_in_location_attr1236 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_location_attr1238 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_TARGET_VALUE_in_location_attr1264 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_c_attributes_attr1332 = new BitSet(
			new long[] { 0x0000000000400000L });
	public static final BitSet FOLLOW_22_in_c_attributes_attr1334 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_c_attributes_attr1336 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_c_attributes_attr1362 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_actions_attr1435 = new BitSet(
			new long[] { 0x0000000000800000L });
	public static final BitSet FOLLOW_23_in_actions_attr1437 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_actions_attr1439 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_actions_attr1465 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_24_in_link1549 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_link1551 = new BitSet(
			new long[] { 0x0000000002000000L });
	public static final BitSet FOLLOW_link_values_in_link1579 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_link_value_in_link_values1658 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_link_values1716 = new BitSet(
			new long[] { 0x0000000002000000L });
	public static final BitSet FOLLOW_link_value_in_link_values1720 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_target_attr_in_link_value1824 = new BitSet(
			new long[] { 0x0000000000008000L });
	public static final BitSet FOLLOW_rel_attr_in_link_value1826 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_self_attr_in_link_value1828 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_category_attr_in_link_value1831 = new BitSet(
			new long[] { 0x0000000000008002L });
	public static final BitSet FOLLOW_attribute_attr_in_link_value1834 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_25_in_target_attr1936 = new BitSet(
			new long[] { 0x0000000000000040L });
	public static final BitSet FOLLOW_TARGET_VALUE_in_target_attr1938 = new BitSet(
			new long[] { 0x000000000C000000L });
	public static final BitSet FOLLOW_26_in_target_attr1968 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_TERM_VALUE_in_target_attr1997 = new BitSet(
			new long[] { 0x0000000008000000L });
	public static final BitSet FOLLOW_27_in_target_attr2028 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_self_attr2104 = new BitSet(
			new long[] { 0x0000000010000000L });
	public static final BitSet FOLLOW_28_in_self_attr2106 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_self_attr2108 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_self_attr2135 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_category_attr2208 = new BitSet(
			new long[] { 0x0000000020000000L });
	public static final BitSet FOLLOW_29_in_category_attr2210 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_category_attr2212 = new BitSet(
			new long[] { 0x0000000000000020L });
	public static final BitSet FOLLOW_QUOTED_VALUE_in_category_attr2239 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_15_in_attribute_attr2311 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attributes_attr_in_attribute_attr2313 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_attribute_kv_attr_in_attributes_attr2386 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_attributes_attr2444 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attribute_kv_attr_in_attributes_attr2448 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_attribute_name_attr_in_attribute_kv_attr2546 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_attribute_kv_attr2548 = new BitSet(
			new long[] { 0x00000000000001A0L });
	public static final BitSet FOLLOW_attribute_value_attr_in_attribute_kv_attr2550 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_TERM_VALUE_in_attribute_name_attr2590 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_set_in_attribute_value_attr0 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_30_in_attribute2660 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_attribute2662 = new BitSet(
			new long[] { 0x0000000000000010L });
	public static final BitSet FOLLOW_attributes_attr_in_attribute2689 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_31_in_location2768 = new BitSet(
			new long[] { 0x0000000000002000L });
	public static final BitSet FOLLOW_13_in_location2770 = new BitSet(
			new long[] { 0x0000000000000200L });
	public static final BitSet FOLLOW_location_values_in_location2797 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_URL_in_location_values2868 = new BitSet(
			new long[] { 0x0000000000004002L });
	public static final BitSet FOLLOW_14_in_location_values2924 = new BitSet(
			new long[] { 0x0000000000000200L });
	public static final BitSet FOLLOW_URL_in_location_values2954 = new BitSet(
			new long[] { 0x0000000000004002L });

}