/*  
Copyright (c) 2008-2011, Intel Performance Learning Solutions Ltd.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
 * Neither the name of Intel Performance Learning Solutions Ltd. nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Intel Performance Learning Solutions Ltd. BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package occi.http.check;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.MismatchedSetException;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;

public class OcciLexer extends Lexer {
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

	public OcciLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}

	public OcciLexer(CharStream input, RecognizerSharedState state) {
		super(input, state);

	}

	public String getGrammarFileName() {
		return "/Occi.g";
	}

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'Category' )
			// 'Category'
			{
				match("Category");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ':' )
			// ':'
			{
				match(':');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ',' )
			// ','
			{
				match(',');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ';' )
			// ';'
			{
				match(';');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'scheme' )
			// 'scheme'
			{
				match("scheme");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( '=' )
			// '='
			{
				match('=');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'class' )
			// 'class'
			{
				match("class");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'title' )
			// 'title'
			{
				match("title");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'rel' )
			// 'rel'
			{
				match("rel");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'location' )
			// 'location'
			{
				match("location");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'attributes' )
			// 'attributes'
			{
				match("attributes");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'actions' )
			// 'actions'
			{
				match("actions");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'Link' )
			// 'Link'
			{
				match("Link");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( '<' )
			// '<'
			{
				match('<');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( '?action=' )
			// '?action='
			{
				match("?action=");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( '>' )
			// '>'
			{
				match('>');

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'self' )
			// 'self'
			{
				match("self");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'category' )
			// 'category'
			{
				match("category");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'X-OCCI-Attribute' )
			// 'X-OCCI-Attribute'
			{
				match("X-OCCI-Attribute");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( 'X-OCCI-Location' )
			// 'X-OCCI-Location'
			{
				match("X-OCCI-Location");

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "T__31"

	// $ANTLR start "URL"
	public final void mURL() throws RecognitionException {
		try {
			int _type = URL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( 'http://' | 'https://' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' ..
			// '9' | '@' | ':' | '%' | '_' | '\\\\' | '+' | '.' | '~' | '#' |
			// '?' | '&' | '/' | '=' | '-' )* )
			// ( 'http://' | 'https://' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9'
			// | '@' | ':' | '%' | '_' | '\\\\' | '+' | '.' | '~' | '#' | '?' |
			// '&' | '/' | '=' | '-' )*
			{
				// ( 'http://' | 'https://' )
				int alt1 = 2;
				int LA1_0 = input.LA(1);

				if ((LA1_0 == 'h')) {
					int LA1_1 = input.LA(2);

					if ((LA1_1 == 't')) {
						int LA1_2 = input.LA(3);

						if ((LA1_2 == 't')) {
							int LA1_3 = input.LA(4);

							if ((LA1_3 == 'p')) {
								int LA1_4 = input.LA(5);

								if ((LA1_4 == ':')) {
									alt1 = 1;
								} else if ((LA1_4 == 's')) {
									alt1 = 2;
								} else {
									NoViableAltException nvae = new NoViableAltException(
											"", 1, 4, input);

									throw nvae;
								}
							} else {
								NoViableAltException nvae = new NoViableAltException(
										"", 1, 3, input);

								throw nvae;
							}
						} else {
							NoViableAltException nvae = new NoViableAltException(
									"", 1, 2, input);

							throw nvae;
						}
					} else {
						NoViableAltException nvae = new NoViableAltException(
								"", 1, 1, input);

						throw nvae;
					}
				} else {
					NoViableAltException nvae = new NoViableAltException("", 1,
							0, input);

					throw nvae;
				}
				switch (alt1) {
				case 1:
					// 'http://'
				{
					match("http://");

				}
					break;
				case 2:
					// 'https://'
				{
					match("https://");

				}
					break;

				}

				// ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '@' | ':' | '%' |
				// '_' | '\\\\' | '+' | '.' | '~' | '#' | '?' | '&' | '/' | '='
				// | '-' )*
				loop2: do {
					int alt2 = 2;
					int LA2_0 = input.LA(1);

					if ((LA2_0 == '#' || (LA2_0 >= '%' && LA2_0 <= '&')
							|| LA2_0 == '+' || (LA2_0 >= '-' && LA2_0 <= ':')
							|| LA2_0 == '=' || (LA2_0 >= '?' && LA2_0 <= 'Z')
							|| LA2_0 == '\\' || LA2_0 == '_'
							|| (LA2_0 >= 'a' && LA2_0 <= 'z') || LA2_0 == '~')) {
						alt2 = 1;
					}

					switch (alt2) {
					case 1: {
						if (input.LA(1) == '#'
								|| (input.LA(1) >= '%' && input.LA(1) <= '&')
								|| input.LA(1) == '+'
								|| (input.LA(1) >= '-' && input.LA(1) <= ':')
								|| input.LA(1) == '='
								|| (input.LA(1) >= '?' && input.LA(1) <= 'Z')
								|| input.LA(1) == '\\' || input.LA(1) == '_'
								|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')
								|| input.LA(1) == '~') {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop2;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "URL"

	// $ANTLR start "DIGITS"
	public final void mDIGITS() throws RecognitionException {
		try {
			int _type = DIGITS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( '0' .. '9' )* )
			// ( '0' .. '9' )*
			{
				// ( '0' .. '9' )*
				loop3: do {
					int alt3 = 2;
					int LA3_0 = input.LA(1);

					if (((LA3_0 >= '0' && LA3_0 <= '9'))) {
						alt3 = 1;
					}

					switch (alt3) {
					case 1:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;

					default:
						break loop3;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "DIGITS"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( '0' .. '9' | '.' )* )
			// ( '0' .. '9' | '.' )*
			{
				// ( '0' .. '9' | '.' )*
				loop4: do {
					int alt4 = 2;
					int LA4_0 = input.LA(1);

					if ((LA4_0 == '.' || (LA4_0 >= '0' && LA4_0 <= '9'))) {
						alt4 = 1;
					}

					switch (alt4) {
					case 1: {
						if (input.LA(1) == '.'
								|| (input.LA(1) >= '0' && input.LA(1) <= '9')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop4;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "FLOAT"

	// $ANTLR start "QUOTE"
	public final void mQUOTE() throws RecognitionException {
		try {
			int _type = QUOTE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( '\"' | '\\'' )
			{
				if (input.LA(1) == '\"' || input.LA(1) == '\'') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "QUOTE"

	// $ANTLR start "TERM_VALUE"
	public final void mTERM_VALUE() throws RecognitionException {
		try {
			int _type = TERM_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( 'a' .. 'z' | 'A..Z' | '0' .. '9' | '-' | '_' | '.' )* )
			// ( 'a' .. 'z' | 'A..Z' | '0' .. '9' | '-' | '_' | '.' )*
			{
				// ( 'a' .. 'z' | 'A..Z' | '0' .. '9' | '-' | '_' | '.' )*
				loop5: do {
					int alt5 = 7;
					switch (input.LA(1)) {
					case 'a':
					case 'b':
					case 'c':
					case 'd':
					case 'e':
					case 'f':
					case 'g':
					case 'h':
					case 'i':
					case 'j':
					case 'k':
					case 'l':
					case 'm':
					case 'n':
					case 'o':
					case 'p':
					case 'q':
					case 'r':
					case 's':
					case 't':
					case 'u':
					case 'v':
					case 'w':
					case 'x':
					case 'y':
					case 'z': {
						alt5 = 1;
					}
						break;
					case 'A': {
						alt5 = 2;
					}
						break;
					case '0':
					case '1':
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9': {
						alt5 = 3;
					}
						break;
					case '-': {
						alt5 = 4;
					}
						break;
					case '_': {
						alt5 = 5;
					}
						break;
					case '.': {
						alt5 = 6;
					}
						break;

					}

					switch (alt5) {
					case 1:
						// 'a' .. 'z'
					{
						matchRange('a', 'z');

					}
						break;
					case 2:
						// 'A..Z'
					{
						match("A..Z");

					}
						break;
					case 3:
						// '0' .. '9'
					{
						matchRange('0', '9');

					}
						break;
					case 4:
						// '-'
					{
						match('-');

					}
						break;
					case 5:
						// '_'
					{
						match('_');

					}
						break;
					case 6:
						// '.'
					{
						match('.');

					}
						break;

					default:
						break loop5;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "TERM_VALUE"

	// $ANTLR start "TARGET_VALUE"
	public final void mTARGET_VALUE() throws RecognitionException {
		try {
			int _type = TARGET_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )* )
			// ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )*
			{
				// ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '/' | '-' | '_' )*
				loop6: do {
					int alt6 = 2;
					int LA6_0 = input.LA(1);

					if ((LA6_0 == '-' || (LA6_0 >= '/' && LA6_0 <= '9')
							|| (LA6_0 >= 'A' && LA6_0 <= 'Z') || LA6_0 == '_' || (LA6_0 >= 'a' && LA6_0 <= 'z'))) {
						alt6 = 1;
					}

					switch (alt6) {
					case 1: {
						if (input.LA(1) == '-'
								|| (input.LA(1) >= '/' && input.LA(1) <= '9')
								|| (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
								|| input.LA(1) == '_'
								|| (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
							input.consume();

						} else {
							MismatchedSetException mse = new MismatchedSetException(
									null, input);
							recover(mse);
							throw mse;
						}

					}
						break;

					default:
						break loop6;
					}
				} while (true);

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "TARGET_VALUE"

	// $ANTLR start "QUOTED_VALUE"
	public final void mQUOTED_VALUE() throws RecognitionException {
		try {
			int _type = QUOTED_VALUE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( QUOTE ( options {greedy=false; } : . )* QUOTE )
			// QUOTE ( options {greedy=false; } : . )* QUOTE
			{
				mQUOTE();
				// ( options {greedy=false; } : . )*
				loop7: do {
					int alt7 = 2;
					int LA7_0 = input.LA(1);

					if ((LA7_0 == '\"' || LA7_0 == '\'')) {
						alt7 = 2;
					} else if (((LA7_0 >= '\u0000' && LA7_0 <= '!')
							|| (LA7_0 >= '#' && LA7_0 <= '&') || (LA7_0 >= '(' && LA7_0 <= '\uFFFF'))) {
						alt7 = 1;
					}

					switch (alt7) {
					case 1:
						// .
					{
						matchAny();

					}
						break;

					default:
						break loop7;
					}
				} while (true);

				mQUOTE();

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "QUOTED_VALUE"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// ( ' ' | '\\t' | '\\r' | '\\n' )
			{
				if ((input.LA(1) >= '\t' && input.LA(1) <= '\n')
						|| input.LA(1) == '\r' || input.LA(1) == ' ') {
					input.consume();

				} else {
					MismatchedSetException mse = new MismatchedSetException(
							null, input);
					recover(mse);
					throw mse;
				}

				_channel = HIDDEN;

			}

			state.type = _type;
			state.channel = _channel;
		} finally {
		}
	}

	// $ANTLR end "WS"

	public void mTokens() throws RecognitionException {
		// T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20
		// | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 |
		// T__29 | T__30 | T__31 | URL | DIGITS | FLOAT | QUOTE | TERM_VALUE |
		// TARGET_VALUE | QUOTED_VALUE | WS )
		int alt8 = 28;
		alt8 = dfa8.predict(input);
		switch (alt8) {
		case 1:
			// T__12
		{
			mT__12();

		}
			break;
		case 2:
			// T__13
		{
			mT__13();

		}
			break;
		case 3:
			// T__14
		{
			mT__14();

		}
			break;
		case 4:
			// T__15
		{
			mT__15();

		}
			break;
		case 5:
			// T__16
		{
			mT__16();

		}
			break;
		case 6:
			// T__17
		{
			mT__17();

		}
			break;
		case 7:
			// T__18
		{
			mT__18();

		}
			break;
		case 8:
			// T__19
		{
			mT__19();

		}
			break;
		case 9:
			// T__20
		{
			mT__20();

		}
			break;
		case 10:
			// T__21
		{
			mT__21();

		}
			break;
		case 11:
			// T__22
		{
			mT__22();

		}
			break;
		case 12:
			// T__23
		{
			mT__23();

		}
			break;
		case 13:
			// T__24
		{
			mT__24();

		}
			break;
		case 14:
			// T__25
		{
			mT__25();

		}
			break;
		case 15:
			// T__26
		{
			mT__26();

		}
			break;
		case 16:
			// T__27
		{
			mT__27();

		}
			break;
		case 17:
			// T__28
		{
			mT__28();

		}
			break;
		case 18:
			// T__29
		{
			mT__29();

		}
			break;
		case 19:
			// T__30
		{
			mT__30();

		}
			break;
		case 20:
			// T__31
		{
			mT__31();

		}
			break;
		case 21:
			// URL
		{
			mURL();

		}
			break;
		case 22:
			// DIGITS
		{
			mDIGITS();

		}
			break;
		case 23:
			// FLOAT
		{
			mFLOAT();

		}
			break;
		case 24:
			// QUOTE
		{
			mQUOTE();

		}
			break;
		case 25:
			// TERM_VALUE
		{
			mTERM_VALUE();

		}
			break;
		case 26:
			// TARGET_VALUE
		{
			mTARGET_VALUE();

		}
			break;
		case 27:
			// QUOTED_VALUE
		{
			mQUOTED_VALUE();

		}
			break;
		case 28:
			// WS
		{
			mWS();

		}
			break;

		}

	}

	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS = "\1\23\1\32\3\uffff\1\37\1\uffff\5\37\1\32\3\uffff\1\32\1\37\1\23"
			+ "\1\uffff\1\53\1\55\1\37\1\32\2\37\2\uffff\1\32\2\37\1\uffff\10\37"
			+ "\2\32\1\37\1\uffff\1\53\2\uffff\1\32\5\37\1\102\3\37\2\32\1\37\1"
			+ "\32\1\37\1\113\3\37\1\uffff\3\37\1\122\1\32\1\37\1\32\1\37\1\uffff"
			+ "\1\130\1\37\1\132\3\37\1\uffff\1\32\1\uffff\1\37\1\32\1\140\1\uffff"
			+ "\1\37\1\uffff\3\37\2\32\1\uffff\3\37\1\152\1\32\1\155\1\156\1\157"
			+ "\1\37\1\uffff\2\32\3\uffff\1\37\2\32\1\166\2\32\1\uffff\11\32\1"
			+ "\u0082\1\u0083\2\uffff";
	static final String DFA8_eofS = "\u0084\uffff";
	static final String DFA8_minS = "\1\11\1\141\3\uffff\1\55\1\uffff\5\55\1\151\3\uffff\3\55\1\uffff"
			+ "\1\55\1\0\1\55\1\56\2\55\2\uffff\1\164\2\55\1\uffff\10\55\1\156"
			+ "\1\117\1\55\1\uffff\1\55\2\uffff\1\145\11\55\1\153\1\103\1\55\1"
			+ "\147\5\55\1\uffff\4\55\1\103\1\55\1\157\1\55\1\uffff\6\55\1\uffff"
			+ "\1\111\1\uffff\1\55\1\162\1\55\1\uffff\1\55\1\uffff\4\55\1\171\1"
			+ "\uffff\4\55\1\101\4\55\1\uffff\1\164\1\157\3\uffff\1\55\1\164\1"
			+ "\143\1\55\1\162\1\141\1\uffff\1\151\1\164\1\142\1\151\1\165\1\157"
			+ "\1\164\1\156\1\145\2\55\2\uffff";
	static final String DFA8_maxS = "\1\172\1\141\3\uffff\1\172\1\uffff\5\172\1\151\3\uffff\1\55\2\172"
			+ "\1\uffff\1\172\1\uffff\1\172\1\56\2\172\2\uffff\1\164\2\172\1\uffff"
			+ "\10\172\1\156\1\117\1\172\1\uffff\1\172\2\uffff\1\145\11\172\1\153"
			+ "\1\103\1\172\1\147\5\172\1\uffff\4\172\1\103\1\172\1\157\1\172\1"
			+ "\uffff\6\172\1\uffff\1\111\1\uffff\1\172\1\162\1\172\1\uffff\1\172"
			+ "\1\uffff\3\172\1\55\1\171\1\uffff\4\172\1\114\4\172\1\uffff\1\164"
			+ "\1\157\3\uffff\1\172\1\164\1\143\1\172\1\162\1\141\1\uffff\1\151"
			+ "\1\164\1\142\1\151\1\165\1\157\1\164\1\156\1\145\2\172\2\uffff";
	static final String DFA8_acceptS = "\2\uffff\1\2\1\3\1\4\1\uffff\1\6\6\uffff\1\16\1\17\1\20\3\uffff"
			+ "\1\26\6\uffff\1\32\1\34\3\uffff\1\31\13\uffff\1\27\1\uffff\1\30"
			+ "\1\33\23\uffff\1\11\10\uffff\1\21\6\uffff\1\15\1\uffff\1\25\3\uffff"
			+ "\1\7\1\uffff\1\10\5\uffff\1\5\11\uffff\1\14\2\uffff\1\1\1\22\1\12"
			+ "\6\uffff\1\13\13\uffff\1\24\1\23";
	static final String DFA8_specialS = "\25\uffff\1\0\156\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\33\2\uffff\1\33\22\uffff\1\33\1\uffff\1\25\4\uffff\1\25\4"
					+ "\uffff\1\3\1\30\1\24\1\32\12\22\1\2\1\4\1\15\1\6\1\17\1\16\1"
					+ "\uffff\1\27\1\32\1\1\10\32\1\14\13\32\1\20\2\32\4\uffff\1\31"
					+ "\1\uffff\1\13\1\26\1\7\4\26\1\21\3\26\1\12\5\26\1\11\1\5\1\10"
					+ "\6\26",
			"\1\34",
			"",
			"",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\2\26\1\35\1\26\1\36\25\26",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\1\42\12\26\1\41\16\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\10\26\1\43\21\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\44\25\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\16\26\1\45\13\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\2\26\1\47\20\26\1\46\6\26",
			"\1\50",
			"",
			"",
			"",
			"\1\51",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\52\6\26",
			"\1\30\1\24\1\32\12\22\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"",
			"\1\37\1\24\1\uffff\12\54\7\uffff\1\37\35\uffff\1\37\1\uffff"
					+ "\32\37",
			"\0\56",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\32\26",
			"\1\37",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\32\26",
			"",
			"",
			"\1\57",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\7\26\1\60\22\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\13\26\1\61\16\26",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\1\62\31\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\63\6\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\64\6\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\13\26\1\65\16\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\2\26\1\66\27\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\67\6\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\70\6\26",
			"\1\71",
			"\1\72",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\73\6\26",
			"",
			"\1\37\1\24\1\uffff\12\54\7\uffff\1\37\35\uffff\1\37\1\uffff"
					+ "\32\37",
			"",
			"",
			"\1\74",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\75\25\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\5\26\1\76\24\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\22\26\1\77\7\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\100\25\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\13\26\1\101\16\26",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\1\103\31\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\21\26\1\104\10\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\10\26\1\105\21\26",
			"\1\106",
			"\1\107",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\17\26\1\110\12\26",
			"\1\111",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\14\26\1\112\15\26",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\22\26\1\114\7\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\6\26\1\115\23\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\116\25\26",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\117\6\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\10\26\1\120\21\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\16\26\1\121\13\26",
			"\1\32\1\uffff\13\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
			"\1\123",
			"\1\30\1\uffff\1\32\12\40\1\124\6\uffff\1\27\31\32\4\uffff\1"
					+ "\31\1\uffff\22\26\1\125\7\26",
			"\1\126",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\127\25\26",
			"",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\16\26\1\131\13\26",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\10\26\1\133\21\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\1\26\1\134\30\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\15\26\1\135\14\26",
			"",
			"\1\136",
			"",
			"\1\30\1\uffff\1\32\12\40\1\124\6\uffff\1\27\31\32\4\uffff\1"
					+ "\31\1\uffff\32\26",
			"\1\137",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\21\26\1\141\10\26",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\16\26\1\142\13\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\24\26\1\143\5\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\22\26\1\144\7\26",
			"\1\145",
			"\1\146",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\30\26\1\147\1\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\15\26\1\150\14\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\23\26\1\151\6\26",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\153\12\uffff\1\154",
			"\1\32\1\uffff\13\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\4\26\1\160\25\26",
			"",
			"\1\161",
			"\1\162",
			"",
			"",
			"",
			"\1\30\1\uffff\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1"
					+ "\uffff\22\26\1\163\7\26",
			"\1\164",
			"\1\165",
			"\1\30\1\37\1\32\12\40\7\uffff\1\27\31\32\4\uffff\1\31\1\uffff"
					+ "\32\26", "\1\167", "\1\170", "", "\1\171", "\1\172",
			"\1\173", "\1\174", "\1\175", "\1\176", "\1\177", "\1\u0080",
			"\1\u0081",
			"\1\32\1\uffff\13\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32",
			"\1\32\1\uffff\13\32\7\uffff\32\32\4\uffff\1\32\1\uffff\32\32", "",
			"" };

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA
			.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA
			.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i = 0; i < numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}

		public String getDescription() {
			return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | URL | DIGITS | FLOAT | QUOTE | TERM_VALUE | TARGET_VALUE | QUOTED_VALUE | WS );";
		}

		public int specialStateTransition(int s, IntStream _input)
				throws NoViableAltException {
			IntStream input = _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA8_21 = input.LA(1);

				s = -1;
				if (((LA8_21 >= '\u0000' && LA8_21 <= '\uFFFF'))) {
					s = 46;
				}

				else
					s = 45;

				if (s >= 0)
					return s;
				break;
			}
			NoViableAltException nvae = new NoViableAltException(
					getDescription(), 8, _s, input);
			error(nvae);
			throw nvae;
		}
	}

}