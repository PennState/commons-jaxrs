/**
 * 
 */
package edu.psu.enumeration;

import static edu.psu.enumeration.Constants.BARE_QUESTION_LOOKUP_REGEX;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ISO 3166-1 alpha-3 codes and ISO 3166-1 alpha-2 codes for countries.
 *
 */
public enum Country 
{
  NSP("NS", "Not Specified"),
  USA("US", "United States"),
  AFG("AF", "Afghanistan"),
  ALA("AX", "Aland Islands"),
  ALB("AL", "Albania"),
  DZA("DZ", "Algeria"),
  ASM("AS", "American Samoa"),
  AND("AD", "Andorra"),
  AGO("AO", "Angola"),
  AIA("AI", "Anguilla"),
  ATA("AQ", "Antarctica"),
  ATG("AG", "Antigua and Barbuda"),
  ARG("AR", "Argentina"),
  ARM("AM", "Armenia"),
  ABW("AW", "Aruba"),
  AUS("AU", "Australia"),
  AUT("AT", "Austria"),
  AZE("AZ", "Azerbaijan"),
  BHS("BS", "Bahamas"),
  BHR("BH", "Bahrain"),
  BGD("BD", "Bangladesh"),
  BRB("BB", "Barbados"),
  BLR("BY", "Belarus"),
  BEL("BE", "Belgium"),
  BLZ("BZ", "Belize"),
  BEN("BJ", "Benin"),
  BMU("BM", "Bermuda"),
  BTN("BT", "Bhutan"),
  BOL("BO", "Bolivia"),
  BES("BQ", "Bonaire, Sint Eustatius and Saba"),
  BIH("BA", "Bosnia and Herzegovina"),
  BWA("BW", "Botswana"),
  BVT("BV", "Bouvet Island"),
  BRA("BR", "Brazil"),
  IOT("IO", "British Indian Ocean Territory"),
  BRN("BN", "Brunei Darussalam"),
  BGR("BG", "Bulgaria"),
  BFA("BF", "Burkina Faso"),
  BDI("BI", "Burundi"),
  KHM("KH", "Cambodia"),
  CMR("CM", "Cameroon"),
  CAN("CA", "Canada"),
  CPV("CV", "Cape Verde"),
  CYM("KY", "Cayman Islands"),
  CAF("CF", "Central African Republic"),
  TCD("TD", "Chad"),
  CHL("CL", "Chile"),
  CHN("CN", "China"),
  CXR("CX", "Christmas Island"),
  CCK("CC", "Cocos (Keeling) Islands"),
  COL("CO", "Colombia"),
  COM("KM", "Comoros"),
  COG("CG", "Congo"),
  COD("CD", "Congo, the Democratic Republic of the"),
  COK("CK", "Cook Islands"),
  CRI("CR", "Costa Rica"),
  CIV("CI", "Cote d'Ivoire"),
  HRV("HR", "Croatia"),
  CUB("CU", "Cuba"),
  CUW("CW", "Curacao"),
  CYP("CY", "Cyprus"),
  CZE("CZ", "Czech Republic"),
  DNK("DK", "Denmark"),
  DJI("DJ", "Djibouti"),
  DMA("DM", "Dominica"),
  DOM("DO", "Dominican Republic"),
  ECU("EC", "Ecuador"),
  EGY("EG", "Egypt"),
  SLV("SV", "El Salvador"),
  GNQ("GQ", "Equatorial Guinea"),
  ERI("ER", "Eritrea"),
  EST("EE", "Estonia"),
  ETH("ET", "Ethiopia"),
  FLK("FK", "Falkland Islands (Malvinas)"),
  FRO("FO", "Faroe Islands"),
  FJI("FJ", "Fiji"),
  FIN("FI", "Finland"),
  FRA("FR", "France"),
  GUF("GF", "French Guiana"),
  PYF("PF", "French Polynesia"),
  ATF("TF", "French Southern Territories"),
  GAB("GA", "Gabon"),
  GMB("GM", "Gambia"),
  GEO("GE", "Georgia"),
  DEU("DE", "Germany"),
  GHA("GH", "Ghana"),
  GIB("GI", "Gibraltar"),
  GRC("GR", "Greece"),
  GRL("GL", "Greenland"),
  GRD("GD", "Grenada"),
  GLP("GP", "Guadeloupe"),
  GUM("GU", "Guam"),
  GTM("GT", "Guatemala"),
  GGY("GG", "Guernsey"),
  GIN("GN", "Guinea"),
  GNB("GW", "Guinea-Bissau"),
  GUY("GY", "Guyana"),
  HTI("HT", "Haiti"),
  HMD("HM", "Heard Island and McDonald Islands"),
  VAT("VA", "Holy See (Vatican City State)"),
  HND("HN", "Honduras"),
  HKG("HK", "Hong Kong"),
  HUN("HU", "Hungary"),
  ISL("IS", "Iceland"),
  IND("IN", "India"),
  IDN("ID", "Indonesia"),
  IRN("IR", "Iran, Islamic Republic of"),
  IRQ("IQ", "Iraq"),
  IRL("IE", "Ireland"),
  IMN("IM", "Isle of Man"),
  ISR("IL", "Israel"),
  ITA("IT", "Italy"),
  JAM("JM", "Jamaica"),
  JPN("JP", "Japan"),
  JEY("JE", "Jersey"),
  JOR("JO", "Jordan"),
  KAZ("KZ", "Kazakhstan"),
  KEN("KE", "Kenya"),
  KIR("KI", "Kiribati"),
  PRK("KP", "Korea, Democratic People's Republic of"),
  KOR("KR", "Korea, Republic of"),
  KWT("KW", "Kuwait"),
  KGZ("KG", "Kyrgyzstan"),
  LAO("LA", "Lao People's Democratic Republic"),
  LVA("LV", "Latvia"),
  LBN("LB", "Lebanon"),
  LSO("LS", "Lesotho"),
  LBR("LR", "Liberia"),
  LBY("LY", "Libya"),
  LIE("LI", "Liechtenstein"),
  LTU("LT", "Lithuania"),
  LUX("LU", "Luxembourg"),
  MAC("MO", "Macao"),
  MKD("MK", "Macedonia, the former Yugoslav Republic of"),
  MDG("MG", "Madagascar"),
  MWI("MW", "Malawi"),
  MYS("MY", "Malaysia"),
  MDV("MV", "Maldives"),
  MLI("ML", "Mali"),
  MLT("MT", "Malta"),
  MHL("MH", "Marshall Islands"),
  MTQ("MQ", "Martinique"),
  MRT("MR", "Mauritania"),
  MUS("MU", "Mauritius"),
  MYT("YT", "Mayotte"),
  MEX("MX", "Mexico"),
  FSM("FM", "Micronesia, Federated States of"),
  MDA("MD", "Moldova, Republic of"),
  MCO("MC", "Monaco"),
  MNG("MN", "Mongolia"),
  MNE("ME", "Montenegro"),
  MSR("MS", "Montserrat"),
  MAR("MA", "Morocco"),
  MOZ("MZ", "Mozambique"),
  MMR("MM", "Myanmar"),
  NAM("NA", "Namibia"),
  NRU("NR", "Nauru"),
  NPL("NP", "Nepal"),
  NLD("NL", "Netherlands"),
  NCL("NC", "New Caledonia"),
  NZL("NZ", "New Zealand"),
  NIC("NI", "Nicaragua"),
  NER("NE", "Niger"),
  NGA("NG", "Nigeria"),
  NIU("NU", "Niue"),
  NFK("NF", "Norfolk Island"),
  MNP("MP", "Northern Mariana Islands"),
  NOR("NO", "Norway"),
  OMN("OM", "Oman"),
  PAK("PK", "Pakistan"),
  PLW("PW", "Palau"),
  PSE("PS", "Palestinian Territory, Occupied"),
  PAN("PA", "Panama"),
  PNG("PG", "Papua New Guinea"),
  PRY("PY", "Paraguay"),
  PER("PE", "Peru"),
  PHL("PH", "Philippines"),
  PCN("PN", "Pitcairn"),
  POL("PL", "Poland"),
  PRT("PT", "Portugal"),
  PRI("PR", "Puerto Rico"),
  QAT("QA", "Qatar"),
  REU("RE", "Reunion"),
  ROU("RO", "Romania"),
  RUS("RU", "Russian Federation"),
  RWA("RW", "Rwanda"),
  BLM("BL", "Saint Barthelemy"),
  SHN("SH", "Saint Helena, Ascension and Tristan da Cunha"),
  KNA("KN", "Saint Kitts and Nevis"),
  LCA("LC", "Saint Lucia"),
  MAF("MF", "Saint Martin (French part)"),
  SPM("PM", "Saint Pierre and Miquelon"),
  VCT("VC", "Saint Vincent and the Grenadines"),
  WSM("WS", "Samoa"),
  SMR("SM", "San Marino"),
  STP("ST", "Sao Tome and Principe"),
  SAU("SA", "Saudi Arabia"),
  SEN("SN", "Senegal"),
  SRB("RS", "Serbia"),
  SYC("SC", "Seychelles"),
  SLE("SL", "Sierra Leone"),
  SGP("SG", "Singapore"),
  SXM("SX", "Sint Maarten (Dutch part)"),
  SVK("SK", "Slovakia"),
  SVN("SI", "Slovenia"),
  SLB("SB", "Solomon Islands"),
  SOM("SO", "Somalia"),
  ZAF("ZA", "South Africa"),
  SGS("GS", "South Georgia and the South Sandwich Islands"),
  SSD("SS", "South Sudan"),
  ESP("ES", "Spain"),
  LKA("LK", "Sri Lanka"),
  SDN("SD", "Sudan"),
  SUR("SR", "Suriname"),
  SJM("SJ", "Svalbard and Jan Mayen"),
  SWZ("SZ", "Swaziland"),
  SWE("SE", "Sweden"),
  CHE("CH", "Switzerland"),
  SYR("SY", "Syrian Arab Republic"),
  TWN("TW", "Taiwan, Province of China"),
  TJK("TJ", "Tajikistan"),
  TZA("TZ", "Tanzania, United Republic of"),
  THA("TH", "Thailand"),
  TLS("TL", "Timor-Leste"),
  TGO("TG", "Togo"),
  TKL("TK", "Tokelau"),
  TON("TO", "Tonga"),
  TTO("TT", "Trinidad and Tobago"),
  TUN("TN", "Tunisia"),
  TUR("TR", "Turkey"),
  TKM("TM", "Turkmenistan"),
  TCA("TC", "Turks and Caicos Islands"),
  TUV("TV", "Tuvalu"),
  UGA("UG", "Uganda"),
  UKR("UA", "Ukraine"),
  ARE("AE", "United Arab Emirates"),
  GBR("GB", "United Kingdom"),
  UMI("UM", "United States Minor Outlying Islands"),
  URY("UY", "Uruguay"),
  UZB("UZ", "Uzbekistan"),
  UNK("XK", "Kosovo"),
  VUT("VU", "Vanuatu"),
  VEN("VE", "Venezuela, Bolivarian Republic of"),
  VNM("VN", "Viet Nam"),
  VGB("VG", "Virgin Islands, British"),
  VIR("VI", "Virgin Islands, U.S."),
  WLF("WF", "Wallis and Futuna"),
  ESH("EH", "Western Sahara"),
  YEM("YE", "Yemen"),
  ZMB("ZM", "Zambia"),
  ZWE("ZW", "Zimbabwe");


  private String twoLetterNotation_;
  private String prettyString_;
  
  private static Map<String, Country> reverseLookup_ = new HashMap<String,Country>();
  
  private static String ILLEGAL_ARGUMENT_MESSAGE = null;
  
  static
  {
    EnumSet<Country> set = EnumSet.allOf(Country.class);
    
    StringBuilder sb = new StringBuilder();
    Iterator<Country> iter = set.iterator();
    
    while(iter.hasNext())
    {
      Country c = iter.next();
      
      reverseLookup_.put(c.prettyString_,  c);
      reverseLookup_.put(c.twoLetterNotation_, c);
      reverseLookup_.put(c.twoLetterNotation_.toLowerCase(), c);
      reverseLookup_.put(c.prettyString_.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase(), c);
      reverseLookup_.put(c.name().toLowerCase(), c);

      sb.append(c.name());
      sb.append(",");
      sb.append(c.twoLetterNotation_);
      if (iter.hasNext())
      {
        sb.append(", ");
      }     
    }

    ILLEGAL_ARGUMENT_MESSAGE = "The value you passed for ETHNICITY was illegal, legal values are: " + sb.toString();
  }
  
  Country(String twoLetterNotation, String prettyName)
  {
    twoLetterNotation_ = twoLetterNotation;
    prettyString_ = prettyName;
  }
  
  /**
   * Attempts to translate a two letter notation to the appropriate enumeration
   * @param notation
   * @return a Country if correct, null if malformed
   */
  public static Country fromTwoLetterNotation(String notation)
  {
    Country country = null;
    if(notation != null) {
      country = reverseLookup_.get(notation.toUpperCase().trim());
    }
    return country;
  }
  
  public static Country fromPrettyString(String prettyString)
  {
    Country country = null;
    if(prettyString != null) {
      country = reverseLookup_.get(prettyString.trim());
    }
    return country;
  }
  
  /**
   * Returns the ISO 3166-1 alpha-2 code for this country
   * @return String
   */
  public String getTwoLetterNotation()
  {
    return twoLetterNotation_;
  }
  
  @Override
  public String toString()
  {
    return prettyString_;
  }
  
  public static Country enumValue(String stringValue)
  {
    if (stringValue == null)
    {
      throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
    }
    
    Country c = null;
    String trimmedValue = stringValue.trim();
    try
    {
      c = Country.valueOf(trimmedValue.toUpperCase());
    }
    catch(IllegalArgumentException ex)
    {
      c = reverseLookup_.get(trimmedValue.toUpperCase());
      
      if (c == null)
      {
        //Try from pretty string
        c = reverseLookup_.get(stringValue.replaceAll(BARE_QUESTION_LOOKUP_REGEX, "").toLowerCase());
        if (c == null)
        {
          throw new IllegalArgumentException(ILLEGAL_ARGUMENT_MESSAGE);
        }
      }
    }
    
    return c;
  }
}
