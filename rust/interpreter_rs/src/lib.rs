use miette::{Context, Error, LabeledSpan};
use std::{borrow::Cow, fmt};

#[derive(Debug, Clone, Copy, PartialEq)]
pub struct Token<'de> {
    origin: &'de str,
    kind: TokenKind,
}

#[derive(Debug, Clone, Copy, PartialEq)]
pub enum TokenKind {
    LeftParen,
    RightParen,
    LeftBrace,
    RightBrace,
    Comma,
    Dot,
    Minus,
    Plus,
    Semicolon,
    Star,
    BangEqual,
    EqualEqual,
    LessEqual,
    GreaterEqual,
    Less,
    Greater,
    Slash,
    Bang,
    Equal,

    String,
    Ident,
    Number(f64),

    // Keywords
    And,
    Class,
    Else,
    False,
    For,
    Fun,
    If,
    Nil,
    Or,
    Return,
    Super,
    This,
    True,
    Var,
    While,
}

impl fmt::Display for Token<'_> {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        let origin = self.origin;
        match self.kind {
            TokenKind::LeftParen => write!(f, "LEFT_PAREN {origin} null"),
            TokenKind::RightParen => write!(f, "RIGHT_PAREN {origin} null"),
            TokenKind::LeftBrace => write!(f, "LEFT_BRACE {origin} null"),
            TokenKind::RightBrace => write!(f, "RIGHT_BRACE {origin} null"),
            TokenKind::Comma => write!(f, "COMMA {origin} null"),
            TokenKind::Dot => write!(f, "DOT {origin} null"),
            TokenKind::Minus => write!(f, "MINUS {origin} null"),
            TokenKind::Plus => write!(f, "PLUS {origin} null"),
            TokenKind::Semicolon => write!(f, "SEMICOLON {origin} null"),
            TokenKind::Star => write!(f, "STAR {origin} null"),
            TokenKind::BangEqual => write!(f, "BANG_EQUAL {origin} null"),
            TokenKind::EqualEqual => write!(f, "EQUAL_EQUAL {origin} null"),
            TokenKind::LessEqual => write!(f, "LESS_EQUAL {origin} null"),
            TokenKind::GreaterEqual => write!(f, "GREATER_EQUAL {origin} null"),
            TokenKind::Less => write!(f, "LESS {origin} null"),
            TokenKind::Greater => write!(f, "GREATER {origin} null"),
            TokenKind::Slash => write!(f, "SLASH {origin} null"),
            TokenKind::Bang => write!(f, "BANG {origin} null"),
            TokenKind::Equal => write!(f, "EQUAL {origin} null"),

            TokenKind::String => write!(f, "STRING {origin} {}", Token::unescape(origin)),
            TokenKind::Ident => write!(f, "IDENTIFIER {origin} null"),
            TokenKind::Number(n) => write!(f, "NUMBER {origin} {n}"),

            TokenKind::And => write!(f, "AND {origin} null"),
            TokenKind::Class => write!(f, "CLASS {origin} null"),
            TokenKind::Else => write!(f, "ELSE {origin} null"),
            TokenKind::False => write!(f, "FALSE {origin} null"),
            TokenKind::For => write!(f, "FOR {origin} null"),
            TokenKind::Fun => write!(f, "FUN {origin} null"),
            TokenKind::If => write!(f, "IF {origin} null"),
            TokenKind::Nil => write!(f, "NIL {origin} null"),
            TokenKind::Or => write!(f, "OR {origin} null"),
            TokenKind::Return => write!(f, "RETURN {origin} null"),
            TokenKind::Super => write!(f, "SUPER {origin} null"),
            TokenKind::This => write!(f, "THIS {origin} null"),
            TokenKind::True => write!(f, "TRUE {origin} null"),
            TokenKind::Var => write!(f, "VAR {origin} null"),
            TokenKind::While => write!(f, "WHILE {origin} null"),
        }
    }
}

impl Token<'_> {
    pub fn unescape<'de>(s: &'de str) -> Cow<'de, str> {
        todo!()
    }
}

pub struct Lexer<'de> {
    whole: &'de str,
    rest: &'de str,
    byte: usize,
}

impl<'de> Lexer<'de> {
    pub fn new(input: &'de str) -> Self {
        Self {
            whole: input,
            rest: input,
            byte: 0,
        }
    }
}

impl<'de> Iterator for Lexer<'de> {
    type Item = Result<Token<'de>, Error>;

    // Figure out the next output token
    fn next(&mut self) -> Option<Self::Item> {
        loop {
            // Must be in the loop for the indices to match up with c_onwards
            let mut chars = self.rest.chars();
            let c = chars.next()?;
            let c_str = &self.rest[..c.len_utf8()];
            let c_onwards = self.rest;
            self.rest = chars.as_str();
            self.byte += c.len_utf8();

            enum Started {
                String,
                Number,
                Ident,
                IfEqualElse(TokenKind, TokenKind),
            }

            let just = move |kind: TokenKind| {
                Some(Ok(Token {
                    kind,
                    origin: c_str,
                }))
            };

            let started =
                match c {
                    '(' => return just(TokenKind::LeftParen),
                    ')' => return just(TokenKind::RightParen),
                    '{' => return just(TokenKind::LeftBrace),
                    '}' => return just(TokenKind::RightBrace),
                    ',' => return just(TokenKind::Comma),
                    '.' => return just(TokenKind::Dot),
                    '-' => return just(TokenKind::Minus),
                    '+' => return just(TokenKind::Plus),
                    ';' => return just(TokenKind::Semicolon),
                    '*' => return just(TokenKind::Star),
                    '/' => return just(TokenKind::Slash),

                    '<' => Started::IfEqualElse(TokenKind::LessEqual, TokenKind::Less),
                    '>' => Started::IfEqualElse(TokenKind::GreaterEqual, TokenKind::Greater),
                    '!' => Started::IfEqualElse(TokenKind::BangEqual, TokenKind::Bang),
                    '=' => Started::IfEqualElse(TokenKind::EqualEqual, TokenKind::Equal),

                    '"' => Started::String,
                    '0'..='9' => Started::Number,
                    'a'..='z' | 'A'..='Z' | '_' => Started::Ident,

                    c if c.is_whitespace() => continue,
                    c => return Some(Err(miette::miette! {
                        labels = vec![
                            LabeledSpan::at(self.byte - c.len_utf8()..self.byte, "this character")
                        ],
                        "Unexpected token '{c}' in input",
                    }
                    .with_source_code(self.whole.to_string()))),
                };

            break match started {
                Started::String => todo!(),

                Started::Number => {
                    let first_non_digit = c_onwards
                        .find(|c| !matches!(c, '.' | '_' | '0'..='9'))
                        .unwrap_or_else(|| c_onwards.len());

                    let mut literal = &c_onwards[..first_non_digit];
                    let mut dotted = literal.splitn(3, '.');
                    if let (Some(one), Some(two), Some(three)) =
                        (dotted.next(), dotted.next(), dotted.next())
                    {
                        literal = *literal[..one.len() + 1 + two.len()];
                    }
                    self.byte += literal.len() - c.len_utf8();
                    // self.rest =

                    let n = match literal.parse() {
                        Ok(n) => n,
                        Err(e) => {
                            return Some(Err(miette::miette! {}));
                        }
                    };
                    return Some(Ok(Token {
                        origin: literal,
                        kind: TokenKind::Number(n),
                    }));
                }

                Started::Ident => todo!(),

                Started::IfEqualElse(yes, no) => {
                    self.rest = self.rest.trim_start();
                    let trimmed = c_onwards.len() - self.rest.len() - 1;
                    self.byte += trimmed;

                    if self.rest.trim_start().starts_with("=") {
                        let span = &c_onwards[..c.len_utf8() + trimmed + 1];
                        self.rest = &self.rest[1..];
                        self.byte += 1;
                        Some(Ok(Token {
                            origin: span,
                            kind: yes,
                        }))
                    } else {
                        Some(Ok(Token {
                            origin: c_str,
                            kind: no,
                        }))
                    }
                }
            };
        }
    }
}

// Omce the iterator returns Err, it will only return `None` - Lazy Iterator
// Allows input to be any type of iterator that can yied `char` - String, &str, Vec<char>
// That is, the input can be turned into an iterator
// fn lex(input: impl IntoIterator<Item = char>) -> impl Iterator<Item = Result<Token, Error>> {
//     Lexer::new(input)
// }
