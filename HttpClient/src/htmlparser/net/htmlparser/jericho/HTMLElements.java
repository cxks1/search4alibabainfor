// Jericho HTML Parser - Java based library for analysing and manipulating HTML
// Version 3.1
// Copyright (C) 2004-2009 Martin Jericho
// http://jericho.htmlparser.net/
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of either one of the following licences:
//
// 1. The Eclipse Public License (EPL) version 1.0,
// included in this distribution in the file licence-epl-1.0.html
// or available at http://www.eclipse.org/legal/epl-v10.html
//
// 2. The GNU Lesser General Public License (LGPL) version 2.1 or later,
// included in this distribution in the file licence-lgpl-2.1.txt
// or available at http://www.gnu.org/licenses/lgpl.txt
//
// This library is distributed on an "AS IS" basis,
// WITHOUT WARRANTY OF ANY KIND, either express or implied.
// See the individual licence texts for more details.

package net.htmlparser.jericho;

import static net.htmlparser.jericho.HTMLElementName.A;
import static net.htmlparser.jericho.HTMLElementName.ABBR;
import static net.htmlparser.jericho.HTMLElementName.ACRONYM;
import static net.htmlparser.jericho.HTMLElementName.ADDRESS;
import static net.htmlparser.jericho.HTMLElementName.APPLET;
import static net.htmlparser.jericho.HTMLElementName.AREA;
import static net.htmlparser.jericho.HTMLElementName.B;
import static net.htmlparser.jericho.HTMLElementName.BASE;
import static net.htmlparser.jericho.HTMLElementName.BASEFONT;
import static net.htmlparser.jericho.HTMLElementName.BDO;
import static net.htmlparser.jericho.HTMLElementName.BIG;
import static net.htmlparser.jericho.HTMLElementName.BLOCKQUOTE;
import static net.htmlparser.jericho.HTMLElementName.BODY;
import static net.htmlparser.jericho.HTMLElementName.BR;
import static net.htmlparser.jericho.HTMLElementName.BUTTON;
import static net.htmlparser.jericho.HTMLElementName.CAPTION;
import static net.htmlparser.jericho.HTMLElementName.CENTER;
import static net.htmlparser.jericho.HTMLElementName.CITE;
import static net.htmlparser.jericho.HTMLElementName.CODE;
import static net.htmlparser.jericho.HTMLElementName.COL;
import static net.htmlparser.jericho.HTMLElementName.COLGROUP;
import static net.htmlparser.jericho.HTMLElementName.DD;
import static net.htmlparser.jericho.HTMLElementName.DEL;
import static net.htmlparser.jericho.HTMLElementName.DFN;
import static net.htmlparser.jericho.HTMLElementName.DIR;
import static net.htmlparser.jericho.HTMLElementName.DIV;
import static net.htmlparser.jericho.HTMLElementName.DL;
import static net.htmlparser.jericho.HTMLElementName.DT;
import static net.htmlparser.jericho.HTMLElementName.EM;
import static net.htmlparser.jericho.HTMLElementName.FIELDSET;
import static net.htmlparser.jericho.HTMLElementName.FONT;
import static net.htmlparser.jericho.HTMLElementName.FORM;
import static net.htmlparser.jericho.HTMLElementName.FRAME;
import static net.htmlparser.jericho.HTMLElementName.FRAMESET;
import static net.htmlparser.jericho.HTMLElementName.H1;
import static net.htmlparser.jericho.HTMLElementName.H2;
import static net.htmlparser.jericho.HTMLElementName.H3;
import static net.htmlparser.jericho.HTMLElementName.H4;
import static net.htmlparser.jericho.HTMLElementName.H5;
import static net.htmlparser.jericho.HTMLElementName.H6;
import static net.htmlparser.jericho.HTMLElementName.HEAD;
import static net.htmlparser.jericho.HTMLElementName.HR;
import static net.htmlparser.jericho.HTMLElementName.HTML;
import static net.htmlparser.jericho.HTMLElementName.I;
import static net.htmlparser.jericho.HTMLElementName.IFRAME;
import static net.htmlparser.jericho.HTMLElementName.IMG;
import static net.htmlparser.jericho.HTMLElementName.INPUT;
import static net.htmlparser.jericho.HTMLElementName.INS;
import static net.htmlparser.jericho.HTMLElementName.ISINDEX;
import static net.htmlparser.jericho.HTMLElementName.KBD;
import static net.htmlparser.jericho.HTMLElementName.LABEL;
import static net.htmlparser.jericho.HTMLElementName.LEGEND;
import static net.htmlparser.jericho.HTMLElementName.LI;
import static net.htmlparser.jericho.HTMLElementName.LINK;
import static net.htmlparser.jericho.HTMLElementName.MAP;
import static net.htmlparser.jericho.HTMLElementName.MENU;
import static net.htmlparser.jericho.HTMLElementName.META;
import static net.htmlparser.jericho.HTMLElementName.NOFRAMES;
import static net.htmlparser.jericho.HTMLElementName.NOSCRIPT;
import static net.htmlparser.jericho.HTMLElementName.OBJECT;
import static net.htmlparser.jericho.HTMLElementName.OL;
import static net.htmlparser.jericho.HTMLElementName.OPTGROUP;
import static net.htmlparser.jericho.HTMLElementName.OPTION;
import static net.htmlparser.jericho.HTMLElementName.P;
import static net.htmlparser.jericho.HTMLElementName.PARAM;
import static net.htmlparser.jericho.HTMLElementName.PRE;
import static net.htmlparser.jericho.HTMLElementName.Q;
import static net.htmlparser.jericho.HTMLElementName.S;
import static net.htmlparser.jericho.HTMLElementName.SAMP;
import static net.htmlparser.jericho.HTMLElementName.SCRIPT;
import static net.htmlparser.jericho.HTMLElementName.SELECT;
import static net.htmlparser.jericho.HTMLElementName.SMALL;
import static net.htmlparser.jericho.HTMLElementName.SPAN;
import static net.htmlparser.jericho.HTMLElementName.STRIKE;
import static net.htmlparser.jericho.HTMLElementName.STRONG;
import static net.htmlparser.jericho.HTMLElementName.STYLE;
import static net.htmlparser.jericho.HTMLElementName.SUB;
import static net.htmlparser.jericho.HTMLElementName.SUP;
import static net.htmlparser.jericho.HTMLElementName.TABLE;
import static net.htmlparser.jericho.HTMLElementName.TBODY;
import static net.htmlparser.jericho.HTMLElementName.TD;
import static net.htmlparser.jericho.HTMLElementName.TEXTAREA;
import static net.htmlparser.jericho.HTMLElementName.TFOOT;
import static net.htmlparser.jericho.HTMLElementName.TH;
import static net.htmlparser.jericho.HTMLElementName.THEAD;
import static net.htmlparser.jericho.HTMLElementName.TITLE;
import static net.htmlparser.jericho.HTMLElementName.TR;
import static net.htmlparser.jericho.HTMLElementName.TT;
import static net.htmlparser.jericho.HTMLElementName.U;
import static net.htmlparser.jericho.HTMLElementName.UL;
import static net.htmlparser.jericho.HTMLElementName.VAR;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Contains static methods which group {@linkplain HTMLElementName HTML element names} by the characteristics of their associated
 * <a href="#HTMLElement">elements</a>.
 * <p>
 * An <i><a name="HTMLElement">HTML element</a></i> is a <a href="Element.html#Normal">normal element</a> with a
 * {@linkplain Element#getName() name} that matches one of the {@linkplain HTMLElementName HTML element names} (ignoring case).
 * This type of element spans the logical HTML element as described in the
 * <a target="_blank" href="http://www.w3.org/TR/html401/intro/sgmltut.html#h-3.2.1">HTML 4.01 specification section 3.2.1</a>,
 * which may be <a href="Element.html#ImplicitlyTerminated">implicitly terminated</a> if it specifies an
 * {@linkplain #getEndTagOptionalElementNames() optional end tag}.
 * <p>
 * The term <i><a name="NonHTMLElement">Non-HTML element</a></i> refers to a <a href="Element.html#Normal">normal element</a>
 * with a {@linkplain Element#getName() name} that does not match one of the {@linkplain HTMLElementName HTML element names}.
 * This type of element must be either a <a href="Element.html#SingleTag">single tag element</a> or
 * <a href="Element.html#ExplicitlyTerminated">explicitly terminated</a>.
 * <p>
 * All of the sets returned by the methods in this class may be modified to customise the behaviour of the parser.
 * Care must be taken however to ensure that the sets only contain tag names in lower case.
 * <p>
 * Below is a table summarising the default characteristics of each <a href="#HTMLElement">HTML element</a>.  See also the
 * <a target="_blank" href="http://www.w3.org/TR/html401/index/elements.html">index of elements in the HTML 4.01 specification</a>
 * for the official table containing similar information.
 * <p>
 * <style type="text/css">
 *  table#ElementSummary td, table#ElementSummary th {padding: 0px 5px 0px 5px}
 *  .StartTagOptionalColumn {text-align: center}
 *  .NestingForbiddenColumn {text-align: center}
 *  .DeprecatedColumn {text-align: center}
 * </style>
 * <table id="ElementSummary" class="bordered" cellspacing="0">
 *  <tr><th title="HTML Element Name">{@linkplain Element#getName() Name}</th><th title="Box Type Block or Inline"><a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#box-gen">Box&nbsp;Type</a></th><th title="Start Tag Optional">{@linkplain #getStartTagOptionalElementNames() Start&nbsp;Tag}</th><th title="End Tag Optional">{@linkplain #getEndTagOptionalElementNames() End&nbsp;Tag}</th><th title="Nesting Forbidden">{@linkplain #getNestingForbiddenElementNames() Nest}</th><th title="Deprecated">{@linkplain #getDeprecatedElementNames() Depr.}</th><th title="Click on the description to view the HTML 4.01 definition of the element">Description / Specification</th></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#A A}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/links.html#edef-A">anchor</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#ABBR ABBR}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-ABBR">abbreviated form (e.g., WWW, HTTP, etc.)</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#ACRONYM ACRONYM}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-ACRONYM">acronym</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#ADDRESS ADDRESS}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-ADDRESS">information on author</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#APPLET APPLET}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-APPLET">Java applet</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#AREA AREA}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-AREA">client-side image map area</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#B B}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-B">bold text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BASE BASE}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/links.html#edef-BASE">document base URI</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BASEFONT BASEFONT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-BASEFONT">base font size</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BDO BDO}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/dirlang.html#edef-BDO">I18N BiDi over-ride</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BIG BIG}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-BIG">large text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BLOCKQUOTE BLOCKQUOTE}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-BLOCKQUOTE">long quotation</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BODY BODY}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag optional">{@linkplain #getStartTagOptionalElementNames() Optional}</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#BODY">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-BODY">document body</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BR BR}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-BR">forced line break</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#BUTTON BUTTON}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-BUTTON">push button</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#CAPTION CAPTION}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-CAPTION">table caption</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#CENTER CENTER}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-CENTER">shorthand for DIV align=center</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#CITE CITE}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-CITE">citation</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#CODE CODE}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-CODE">computer code fragment</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#COL COL}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-COL">table column</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#COLGROUP COLGROUP}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#COLGROUP">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-COLGROUP">table column group</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DD DD}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#DD">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-DD">definition description</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DEL DEL}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-DEL">deleted text</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DFN DFN}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-DFN">instance definition</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DIR DIR}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-DIR">directory list</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DIV DIV}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-DIV">generic language/style container</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DL DL}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-DL">definition list</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#DT DT}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#DT">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-DT">definition term</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#EM EM}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-EM">emphasis</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#FIELDSET FIELDSET}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-FIELDSET">form control group</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#FONT FONT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-FONT">local change to font</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#FORM FORM}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-FORM">interactive form</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#FRAME FRAME}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/frames.html#edef-FRAME">subwindow</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#FRAMESET FRAMESET}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/frames.html#edef-FRAMESET">window subdivision</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H1 H1}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H1">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H2 H2}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H2">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H3 H3}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H3">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H4 H4}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H4">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H5 H5}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H5">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#H6 H6}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-H6">heading</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#HEAD HEAD}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag optional">{@linkplain #getStartTagOptionalElementNames() Optional}</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#HEAD">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-HEAD">document head</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#HR HR}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-HR">horizontal rule</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#HTML HTML}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag optional">{@linkplain #getStartTagOptionalElementNames() Optional}</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#HTML">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-HTML">document root element</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#I I}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-I">italic text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#IFRAME IFRAME}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/frames.html#edef-IFRAME">inline subwindow</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#IMG IMG}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-IMG">Embedded image</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#INPUT INPUT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-INPUT">form control</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#INS INS}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-INS">inserted text</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#ISINDEX ISINDEX}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-ISINDEX">single line prompt</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#KBD KBD}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-KBD">text to be entered by the user</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#LABEL LABEL}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-LABEL">form field label text</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#LEGEND LEGEND}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-LEGEND">fieldset legend</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#LI LI}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#LI">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-LI">list item</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#LINK LINK}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/links.html#edef-LINK">a media-independent link</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#MAP MAP}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-MAP">client-side image map</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#MENU MENU}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-MENU">menu list</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#META META}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-META">generic metainformation</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#NOFRAMES NOFRAMES}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/frames.html#edef-NOFRAMES">alternate content container for non frame-based rendering</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#NOSCRIPT NOSCRIPT}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/scripts.html#edef-NOSCRIPT">alternate content container for non script-based rendering</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#OBJECT OBJECT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-OBJECT">generic embedded object</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#OL OL}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-OL">ordered list</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#OPTGROUP OPTGROUP}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-OPTGROUP">option group</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#OPTION OPTION}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#OPTION">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-OPTION">selectable choice</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#P P}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#P">details</a>)</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-P">paragraph</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#PARAM PARAM}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag forbidden">{@linkplain #getEndTagForbiddenElementNames() Forbidden}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/objects.html#edef-PARAM">named property value</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#PRE PRE}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-PRE">preformatted text</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#Q Q}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-Q">short inline quotation</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#S S}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-S">strike-through text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SAMP SAMP}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-SAMP">sample program output, scripts, etc.</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SCRIPT SCRIPT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/scripts.html#edef-SCRIPT">script statements</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SELECT SELECT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-SELECT">option selector</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SMALL SMALL}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-SMALL">small text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SPAN SPAN}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-SPAN">generic language/style container</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#STRIKE STRIKE}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-STRIKE">strike-through text</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#STRONG STRONG}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-STRONG">strong emphasis</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#STYLE STYLE}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/styles.html#edef-STYLE">style info</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SUB SUB}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-SUB">subscript</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#SUP SUP}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-SUP">superscript</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TABLE TABLE}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TABLE">table</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TBODY TBODY}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag optional">{@linkplain #getStartTagOptionalElementNames() Optional}</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#TBODY">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TBODY">table body</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TD TD}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#TD">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TD">table data cell</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TEXTAREA TEXTAREA}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/interact/forms.html#edef-TEXTAREA">multi-line text field</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TFOOT TFOOT}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#TFOOT">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TFOOT">table footer</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TH TH}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#TH">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TH">table header cell</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#THEAD THEAD}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#THEAD">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-THEAD">table header</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TITLE TITLE}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting forbidden">{@linkplain #getNestingForbiddenElementNames() NF}</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#edef-TITLE">document title</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TR TR}</td><td class="BoxTypeColumn" title="neither block-level nor inline-level element">&nbsp;</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag optional">{@linkplain #getEndTagOptionalElementNames() Optional}&nbsp;(<a title="View information about which tags terminate this element" href="HTMLElementName.html#TR">details</a>)</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/tables.html#edef-TR">table row</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#TT TT}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-TT">teletype or monospaced text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#U U}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="deprecated"><a target="blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">D</a></td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/present/graphics.html#edef-U">underlined text style</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#UL UL}</td><td class="BoxTypeColumn" title="block-level element">{@linkplain #getBlockLevelElementNames() Block}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/lists.html#edef-UL">unordered list</a></td></tr>
 *  <tr><td title="View the field definition in the HTMLElementName class" class="ElementNameColumn">{@link HTMLElementName#VAR VAR}</td><td class="BoxTypeColumn" title="inline-level element">{@linkplain #getInlineLevelElementNames() Inline}</td><td class="StartTagOptionalColumn" title="start tag not optional">&nbsp;</td><td class="EndTagOptionalColumn" title="end tag required">{@linkplain #getEndTagRequiredElementNames() Required}</td><td class="NestingForbiddenColumn" title="nesting allowed">&nbsp;</td><td class="DeprecatedColumn" title="not deprecated">&nbsp;</td><td class="ElementDescription"><a title="View the HTML 4.01 definition of this element" target="_blank" href="http://www.w3.org/TR/html401/struct/text.html#edef-VAR">instance of a variable or program argument</a></td></tr>
 * </table>
 *
 * @see HTMLElementName
 * @see Element
 */
public final class HTMLElements {
	private static final List<String> ALL=new ArrayList<String>(Arrays.asList(new String[] {A,ABBR,ACRONYM,ADDRESS,APPLET,AREA,B,BASE,BASEFONT,BDO,BIG,BLOCKQUOTE,BODY,BR,BUTTON,CAPTION,CENTER,CITE,CODE,COL,COLGROUP,DD,DEL,DFN,DIR,DIV,DL,DT,EM,FIELDSET,FONT,FORM,FRAME,FRAMESET,H1,H2,H3,H4,H5,H6,HEAD,HR,HTML,I,IFRAME,IMG,INPUT,INS,ISINDEX,KBD,LABEL,LEGEND,LI,LINK,MAP,MENU,META,NOFRAMES,NOSCRIPT,OBJECT,OL,OPTGROUP,OPTION,P,PARAM,PRE,Q,S,SAMP,SCRIPT,SELECT,SMALL,SPAN,STRIKE,STRONG,STYLE,SUB,SUP,TABLE,TBODY,TD,TEXTAREA,TFOOT,TH,THEAD,TITLE,TR,TT,U,UL,VAR}));
	private static final HTMLElementNameSet BLOCK=new HTMLElementNameSet(new String[] {P,H1,H2,H3,H4,H5,H6,UL,OL,DIR,MENU,PRE,DL,DIV,CENTER,NOSCRIPT,NOFRAMES,BLOCKQUOTE,FORM,ISINDEX,HR,TABLE,FIELDSET,ADDRESS});
	private static final HTMLElementNameSet INLINE=new HTMLElementNameSet(new String[] {TT,I,B,U,S,STRIKE,BIG,SMALL,EM,STRONG,DFN,CODE,SAMP,KBD,VAR,CITE,ABBR,ACRONYM,A,IMG,APPLET,OBJECT,FONT,BASEFONT,BR,SCRIPT,MAP,Q,SUB,SUP,SPAN,BDO,IFRAME,INPUT,SELECT,TEXTAREA,LABEL,BUTTON,INS,DEL});

	private static final HTMLElementNameSet END_TAG_FORBIDDEN_SET=new HTMLElementNameSet(new String[] {AREA,BASE,BASEFONT,BR,COL,FRAME,HR,IMG,INPUT,ISINDEX,LINK,META,PARAM});

	private static final HTMLElementNameSet _UL_OL=new HTMLElementNameSet(UL).union(OL);
	private static final HTMLElementNameSet _DD_DT=new HTMLElementNameSet(DD).union(DT);
	private static final HTMLElementNameSet _THEAD_TBODY_TFOOT_TR=new HTMLElementNameSet(THEAD).union(TBODY).union(TFOOT).union(TR);
	private static final HTMLElementNameSet _THEAD_TBODY_TFOOT_TR_TD_TH=new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR).union(TD).union(TH);

	private static final HTMLElementNameSet DEPRECATED=new HTMLElementNameSet().union(APPLET).union(BASEFONT).union(CENTER).union(DIR).union(FONT).union(ISINDEX).union(MENU).union(S).union(STRIKE).union(U);
	private static final HTMLElementNameSet START_TAG_OPTIONAL_SET=new HTMLElementNameSet().union(BODY).union(HEAD).union(HTML).union(TBODY);

	private static final HashMap<String,String> CONSTANT_NAME_MAP=buildTagMap(); // contains a map of tag names to the tag constants, allowing standard tags to be tested using == operator instead of equals()
	private static final HashMap<String,HTMLElementTerminatingTagNameSets> TERMINATING_TAG_NAME_SETS_MAP=buildTerminatingTagNameSetsMap(); // contains a map of tags having optional end tags to the HTMLElementTerminatingTagNameSets that can terminate the element if the end tag is not present
	private static final Set<String> END_TAG_OPTIONAL_SET=TERMINATING_TAG_NAME_SETS_MAP.keySet();
	private static final HTMLElementNameSet END_TAG_REQUIRED_SET=new HTMLElementNameSet().union(ALL).minus(END_TAG_FORBIDDEN_SET).minus(END_TAG_OPTIONAL_SET);
	private static final HTMLElementNameSet CLOSING_SLASH_IGNORED_SET=new HTMLElementNameSet().union(END_TAG_OPTIONAL_SET).union(END_TAG_REQUIRED_SET);

	static final HTMLElementNameSet END_TAG_REQUIRED_NESTING_FORBIDDEN_SET=new HTMLElementNameSet().union(A).union(ADDRESS).union(APPLET).union(BUTTON).union(CAPTION).union(FORM).union(IFRAME).union(LABEL).union(LEGEND).union(OPTGROUP).union(SCRIPT).union(SELECT).union(STYLE).union(TEXTAREA).union(TITLE);
	private static final HTMLElementNameSet END_TAG_OPTIONAL_NESTING_FORBIDDEN_SET=new HTMLElementNameSet().union(BODY).union(COLGROUP).union(HEAD).union(HTML).union(OPTION).union(P);
	private static final HTMLElementNameSet NESTING_FORBIDDEN_SET=new HTMLElementNameSet().union(END_TAG_REQUIRED_NESTING_FORBIDDEN_SET).union(END_TAG_OPTIONAL_NESTING_FORBIDDEN_SET).union(END_TAG_FORBIDDEN_SET);

	private HTMLElements() {}

	/**
	 * Returns a list containing all of the {@linkplain HTMLElementName HTML element names}.
	 * <p>
	 * The returned list is in alphabetical order.
	 *
	 * @return a list containing of all the {@linkplain HTMLElementName HTML element names}.
	 */
	public static final List<String> getElementNames() {
		return ALL;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all the
	 * <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q5">block-level elements</a>.
	 * <p>
	 * The element names contained in this set are:<br />
	 * {@link HTMLElementName#ADDRESS ADDRESS}, {@link HTMLElementName#BLOCKQUOTE BLOCKQUOTE}, {@link HTMLElementName#CENTER CENTER}, {@link HTMLElementName#DIR DIR}, {@link HTMLElementName#DIV DIV},
	 * {@link HTMLElementName#DL DL}, {@link HTMLElementName#FIELDSET FIELDSET}, {@link HTMLElementName#FORM FORM},
	 * {@link HTMLElementName#H1 H1}, {@link HTMLElementName#H2 H2}, {@link HTMLElementName#H3 H3}, {@link HTMLElementName#H4 H4}, {@link HTMLElementName#H5 H5}, {@link HTMLElementName#H6 H6},
	 * {@link HTMLElementName#HR HR}, {@link HTMLElementName#ISINDEX ISINDEX}, {@link HTMLElementName#MENU MENU}, {@link HTMLElementName#NOFRAMES NOFRAMES}, {@link HTMLElementName#NOSCRIPT NOSCRIPT},
	 * {@link HTMLElementName#OL OL}, {@link HTMLElementName#P P}, {@link HTMLElementName#PRE PRE}, {@link HTMLElementName#TABLE TABLE}, {@link HTMLElementName#UL UL} 
	 * <p>
	 * This set is defined in the <a target="_blank" href="http://www.w3.org/TR/html401/sgml/loosedtd.html">HTML 4.01 Transitional DTD</a>,
	 * but more detailed information can be found in the 
	 * <a target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#h-7.5.3">HTML 4.01 specification section 7.5.3 - Block-level and inline elements</a>
	 * and the <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q5">CSS2 specification section 9.2.1 - Block-level elements and block boxes</a>. 
	 * <p>
	 * The CSS2 <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#display-prop">display</a> property can be used
	 * to override the normal box type of an element.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all the <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q5">block-level elements</a>.
	 * @see #getInlineLevelElementNames()
	 */
	public static Set<String> getBlockLevelElementNames() {
		return BLOCK;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all the
	 * <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q7">inline-level elements</a>.
	 * <p>
	 * The element names contained in this set are:<br />
	 * {@link HTMLElementName#A A}, {@link HTMLElementName#ABBR ABBR}, {@link HTMLElementName#ACRONYM ACRONYM}, {@link HTMLElementName#APPLET APPLET}, {@link HTMLElementName#B B}, {@link HTMLElementName#BASEFONT BASEFONT},
	 * {@link HTMLElementName#BDO BDO}, {@link HTMLElementName#BIG BIG}, {@link HTMLElementName#BR BR}, {@link HTMLElementName#BUTTON BUTTON}, {@link HTMLElementName#CITE CITE}, {@link HTMLElementName#CODE CODE},
	 * {@link HTMLElementName#DEL DEL}, {@link HTMLElementName#DFN DFN}, {@link HTMLElementName#EM EM}, {@link HTMLElementName#FONT FONT}, {@link HTMLElementName#I I}, {@link HTMLElementName#IFRAME IFRAME}, {@link HTMLElementName#IMG IMG},
	 * {@link HTMLElementName#INPUT INPUT}, {@link HTMLElementName#INS INS}, {@link HTMLElementName#KBD KBD}, {@link HTMLElementName#LABEL LABEL}, {@link HTMLElementName#MAP MAP}, {@link HTMLElementName#OBJECT OBJECT},
	 * {@link HTMLElementName#Q Q}, {@link HTMLElementName#S S}, {@link HTMLElementName#SAMP SAMP}, {@link HTMLElementName#SCRIPT SCRIPT}, {@link HTMLElementName#SELECT SELECT}, {@link HTMLElementName#SMALL SMALL},
	 * {@link HTMLElementName#SPAN SPAN}, {@link HTMLElementName#STRIKE STRIKE}, {@link HTMLElementName#STRONG STRONG}, {@link HTMLElementName#SUB SUB}, {@link HTMLElementName#SUP SUP}, {@link HTMLElementName#TEXTAREA TEXTAREA},
	 * {@link HTMLElementName#TT TT}, {@link HTMLElementName#U U}, {@link HTMLElementName#VAR VAR}	 
	 * <p>
	 * This set is defined in the <a target="_blank" href="http://www.w3.org/TR/html401/sgml/loosedtd.html">HTML 4.01 Transitional DTD</a>,
	 * but more detailed information can be found in the 
	 * <a target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#h-7.5.3">HTML 4.01 specification section 7.5.3 - Block-level and inline elements</a>
	 * and the <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q7">CSS2 specification section 9.2.2 - Inline-level elements and inline boxes</a>. 
	 * <p>
	 * The CSS2 <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#display-prop">display</a> property can be used
	 * to override the normal box type of an element.
	 * <p>
	 * The <a target="_blank" href="http://www.w3.org/TR/html401/struct/global.html#h-7.2">HTML Document Type Definitions</a>
	 * forbid the presence of {@linkplain #getBlockLevelElementNames() block-level elements} inside inline-level elements,
	 * but it is tolerated by all popular browsers in various situations, even in XHTML documents.
	 * The most notorious example of this is the common inclusion of block-level elements inside {@link HTMLElementName#FONT FONT} elements.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all the <a target="_blank" href="http://www.w3.org/TR/REC-CSS2/visuren.html#q7">inline-level elements</a>.
	 * @see #getBlockLevelElementNames()
	 */
	public static Set<String> getInlineLevelElementNames() {
		return INLINE;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all
	 * <a target="_blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">deprecated</a> elements in HTML 4.01.
	 * @return a set containing the {@linkplain Element#getName() names} of all <a target="_blank" href="http://www.w3.org/TR/html401/conform.html#deprecated">deprecated</a> elements in HTML 4.01.
	 */
	public static Set<String> getDeprecatedElementNames() {
		return DEPRECATED;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a>
	 * for which the {@linkplain Element#getEndTag() end tag} is <i>forbidden</i>.
	 * <p>
	 * See the <a href="Element.html#ParsingRulesHTMLEndTagForbidden">element parsing rules for HTML elements with forbidden end tags</a>
	 * for more information.
	 * <p>
	 * The <a target="_blank" href="http://www.w3.org/TR/html401/index/elements.html">index of elements in the HTML 4.01 specification</a>
	 * includes the letter '<b>F</b>' in the "<b>End Tag</b>" column for elements whose end tag is forbidden.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a> for which the {@linkplain Element#getEndTag() end tag} is <i>forbidden</i>.
	 * @see #getEndTagOptionalElementNames()
	 * @see #getEndTagRequiredElementNames()
	 */
	public static Set<String> getEndTagForbiddenElementNames() {
		return END_TAG_FORBIDDEN_SET;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a>
	 * for which the {@linkplain Element#getEndTag() end tag} is <i>optional</i>.
	 * <p>
	 * Elements with these names may be <a href="Element.html#ImplicitlyTerminated">implicitly terminated</a> by a subsequent
	 * {@linkplain #getTerminatingStartTagNames(String) terminating start tag} or
	 * {@linkplain #getTerminatingEndTagNames(String) terminating end tag}.
	 * A list of the these terminating tags, and the names of {@linkplain #getNonterminatingElementNames(String) non-terminating elements}
	 * that can be nested within the element, can be found in the documentation of each relevant element in the {@link HTMLElementName} class.
	 * <p>
	 * See the <a href="Element.html#ParsingRulesHTMLEndTagOptional">element parsing rules for HTML elements with optional end tags</a>
	 * for more information.
	 * <p>
	 * The <a target="_blank" href="http://www.w3.org/TR/html401/index/elements.html">index of elements in the HTML 4.01 specification</a>
	 * includes the letter '<b>O</b>' in the "<b>End Tag</b>" column for elements whose end tag is optional.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a> for which the {@linkplain Element#getEndTag() end tag} is <i>optional</i>.
	 * @see #getEndTagForbiddenElementNames()
	 * @see #getEndTagRequiredElementNames()
	 */
	public static Set<String> getEndTagOptionalElementNames() {
		return END_TAG_OPTIONAL_SET;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a>
	 * for which the {@linkplain Element#getEndTag() end tag} is <i>required</i>.
	 * <p>
	 * See the <a href="Element.html#ParsingRulesHTMLEndTagRequired">element parsing rules for HTML elements with required end tags</a>
	 * for more information.
	 * <p>
	 * The <a target="_blank" href="http://www.w3.org/TR/html401/index/elements.html">index of elements in the HTML 4.01 specification</a>
	 * leaves the "<b>End Tag</b>" column blank for elements whose end tag is required.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a> for which the {@linkplain Element#getEndTag() end tag} is <i>required</i>.
	 * @see #getEndTagForbiddenElementNames()
	 * @see #getEndTagOptionalElementNames()
	 */
	public static Set<String> getEndTagRequiredElementNames() {
		return END_TAG_REQUIRED_SET;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a>
	 * for which the {@linkplain Element#getStartTag() start tag} is optional.
	 * <p>
	 * Elements with optional start tags must be present in the <a target="_blank" href="http://www.w3.org/DOM/">document object model</a> (DOM)
	 * in certain locations, either forming part of the structure of the HTML document as a whole 
	 * (e.g. the {@link HTMLElementName#HTML HTML}, {@link HTMLElementName#HEAD HEAD}, and {@link HTMLElementName#BODY BODY} elements),
	 * or forming part of the structure of a {@link HTMLElementName#TABLE TABLE} element (e.g. the {@link HTMLElementName#TBODY TBODY} element).
	 * The location of an <a target="_blank" href="http://www.w3.org/TR/html401/intro/sgmltut.html#idx-element-9">omitted</a> start tag
	 * in the document's object model can be inferred from the surrounding elements.
	 * <p>
	 * This library does not use this property in any way when parsing documents, and does not construct a document object model from the
	 * source, so no implied element is created where an optional start tag is omitted.
	 * <p>
	 * When the start tag has been omitted in the document text, the corresponding end tag should also be omitted.
	 * <p>
	 * The <a target="_blank" href="http://www.w3.org/TR/html401/index/elements.html">index of elements in the HTML 4.01 specification</a>
	 * includes the letter '<b>O</b>' in the "<b>Start Tag</b>" column for elements whose start tag is optional.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a> for which the {@linkplain Element#getStartTag() start tag} is optional.
	 */
	public static Set<String> getStartTagOptionalElementNames() {
		return START_TAG_OPTIONAL_SET;
	}

	/**
	 * Returns the {@linkplain StartTag#getName() names} of start tags that <a href="#ImplicitlyTerminated">implicitly terminate</a>
	 * an <a href="#HTMLElement">HTML element</a> with the specified name.
	 * <p>
	 * This method is only relevant to <a href="#HTMLElement">HTML elements</a> for which the
	 * {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * It returns <code>null</code> if
	 * <br />{@link #getEndTagOptionalElementNames()}<code>.contains(endTagOptionalElementName.toLowerCase())==null</code>.
	 *
	 * @param endTagOptionalElementName  the {@linkplain Element#getName() name} of an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @return the {@linkplain StartTag#getName() names} of start tags that <a href="Element.html#ImplicitlyTerminated">implicitly terminate</a> an <a href="#HTMLElement">HTML element</a> with the specified name, or <code>null</code> if the name does not identify an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @see #getTerminatingEndTagNames(String endTagOptionalElementName)
	 * @see #getNonterminatingElementNames(String endTagOptionalElementName)
	 */
	public static Set<String> getTerminatingStartTagNames(final String endTagOptionalElementName) {
		final HTMLElementTerminatingTagNameSets terminatingTagNameSets=getTerminatingTagNameSets(endTagOptionalElementName);
		if (terminatingTagNameSets==null) return null;
		return terminatingTagNameSets.TerminatingStartTagNameSet;
	}

	/**
	 * Returns the {@linkplain EndTag#getName() names} of end tags that <a href="#ImplicitlyTerminated">implicitly terminate</a>
	 * an <a href="#HTMLElement">HTML element</a> with the specified name.
	 * <p>
	 * This method is only relevant to <a href="#HTMLElement">HTML elements</a> for which the
	 * {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * It returns <code>null</code> if
	 * <br />{@link #getEndTagOptionalElementNames()}<code>.contains(endTagOptionalElementName.toLowerCase())==null</code>.
	 * <p>
	 * Note that removing the tag name matching the specified element has no effect on the behaviour of the parser,
	 * as it is always assumed that a start tag is terminated by an end tag with a matching name.
	 *
	 * @param endTagOptionalElementName  the {@linkplain Element#getName() name} of an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @return the {@linkplain StartTag#getName() names} of end tags that <a href="Element.html#ImplicitlyTerminated">implicitly terminate</a> an <a href="#HTMLElement">HTML element</a> with the specified name, or <code>null</code> if the name does not identify an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @see #getTerminatingStartTagNames(String endTagOptionalElementName)
	 * @see #getNonterminatingElementNames(String endTagOptionalElementName)
	 */
	public static Set<String> getTerminatingEndTagNames(final String endTagOptionalElementName) {
		final HTMLElementTerminatingTagNameSets terminatingTagNameSets=getTerminatingTagNameSets(endTagOptionalElementName);
		if (terminatingTagNameSets==null) return null;
		return terminatingTagNameSets.TerminatingEndTagNameSet;
	}

	/**
	 * Returns the {@linkplain Element#getName() names} of elements that do NOT <a href="#ImplicitlyTerminated">implicitly terminate</a>
	 * an <a href="#HTMLElement">HTML element</a> with the specified name.
	 * Neither can any tag nested inside any of these elements <a href="#ImplicitlyTerminated">implicitly terminate</a> the specified element,
	 * even if it is listed as one of the {@linkplain #getTerminatingStartTagNames(String) terminating start tags} or
	 * {@linkplain #getTerminatingEndTagNames(String) terminating end tags}.
	 * <p>
	 * This method is only relevant to <a href="#HTMLElement">HTML elements</a> for which the
	 * {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * It returns <code>null</code> if
	 * <br />{@link #getEndTagOptionalElementNames()}<code>.contains(endTagOptionalElementName.toLowerCase())==null</code>.
	 *
	 * @param endTagOptionalElementName  the {@linkplain Element#getName() name} of an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @return the {@linkplain Element#getName() names} of elements that do NOT <a href="#ImplicitlyTerminated">implicitly terminate</a> an <a href="#HTMLElement">HTML element</a> with the specified name, or <code>null</code> if the name does not identify an element for which the {@linkplain #getEndTagOptionalElementNames() end tag is optional}.
	 * @see #getTerminatingStartTagNames(String endTagOptionalElementName)
	 * @see #getTerminatingEndTagNames(String endTagOptionalElementName)
	 */
	public static Set<String> getNonterminatingElementNames(final String endTagOptionalElementName) {
		final HTMLElementTerminatingTagNameSets terminatingTagNameSets=getTerminatingTagNameSets(endTagOptionalElementName);
		if (terminatingTagNameSets==null) return null;
		return terminatingTagNameSets.NonterminatingElementNameSet;
	}

	/**
	 * Returns a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a>
	 * which should never contain elements of the same name, either as direct or indirect descendants.
	 *
	 * @return a set containing the {@linkplain Element#getName() names} of all of the <a href="#HTMLElement">HTML elements</a> which should never contain elements of the same name.
	 */
	public static Set<String> getNestingForbiddenElementNames() {
		return NESTING_FORBIDDEN_SET;
	}

	static final String getConstantElementName(final String elementName) {
		final String elementNameConstant=CONSTANT_NAME_MAP.get(elementName);
		return elementNameConstant!=null ? elementNameConstant : elementName;
	}

	static final boolean isClosingSlashIgnored(final String elementName) {
		return CLOSING_SLASH_IGNORED_SET.contains(elementName);
	}

	static final HTMLElementTerminatingTagNameSets getTerminatingTagNameSets(final String endTagOptionalElementName) {
		return TERMINATING_TAG_NAME_SETS_MAP.get(endTagOptionalElementName);
	}

	private static HashMap<String,HTMLElementTerminatingTagNameSets> buildTerminatingTagNameSetsMap() {
		// HTML is included in the NonterminatingElementNameSet of BODY and HTML in case the source contains (illegaly) nested HTML documents
		final HashMap<String,HTMLElementTerminatingTagNameSets> map=new HashMap<String,HTMLElementTerminatingTagNameSets>(20,1.0F); // 15 entries in total
		map.put(BODY,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(), new HTMLElementNameSet(HTML).union(BODY), new HTMLElementNameSet(HTML)));
		map.put(COLGROUP,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR).union(COLGROUP), new HTMLElementNameSet(TABLE).union(COLGROUP), new HTMLElementNameSet(TABLE)));
		map.put(DD,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_DD_DT), new HTMLElementNameSet(DL).union(DD), new HTMLElementNameSet(DL)));
		map.put(DT,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_DD_DT), new HTMLElementNameSet(DL).union(DT), new HTMLElementNameSet(DL)));
		map.put(HEAD,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(BODY).union(FRAMESET), new HTMLElementNameSet(HTML).union(HEAD), new HTMLElementNameSet()));
		map.put(HTML,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(), new HTMLElementNameSet(HTML), new HTMLElementNameSet(HTML)));
		map.put(LI,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(LI), new HTMLElementNameSet(_UL_OL).union(LI), new HTMLElementNameSet(_UL_OL)));
		map.put(OPTION,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(OPTION).union(OPTGROUP), new HTMLElementNameSet(SELECT).union(OPTION), new HTMLElementNameSet()));
		map.put(P,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(BLOCK).union(_DD_DT).union(TH).union(TD).union(LI), new HTMLElementNameSet(BLOCK).union(_DD_DT).union(BODY).union(HTML).union(_THEAD_TBODY_TFOOT_TR_TD_TH).union(CAPTION).union(LEGEND), new HTMLElementNameSet()));
		map.put(TBODY,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(TBODY).union(TFOOT).union(THEAD), new HTMLElementNameSet(TABLE).union(TBODY), new HTMLElementNameSet(TABLE)));
		map.put(TD,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR_TD_TH), new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR).union(TABLE).union(TD), new HTMLElementNameSet(TABLE)));
		map.put(TFOOT,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(TBODY).union(TFOOT).union(THEAD), new HTMLElementNameSet(TABLE).union(TFOOT), new HTMLElementNameSet(TABLE)));
		map.put(TH,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR_TD_TH), new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR).union(TABLE).union(TH), new HTMLElementNameSet(TABLE)));
		map.put(THEAD,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(TBODY).union(TFOOT).union(THEAD), new HTMLElementNameSet(TABLE).union(THEAD), new HTMLElementNameSet(TABLE)));
		map.put(TR,new HTMLElementTerminatingTagNameSets(new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR), new HTMLElementNameSet(_THEAD_TBODY_TFOOT_TR).union(TABLE), new HTMLElementNameSet(TABLE)));
		return map;
	}
	
	private static HashMap<String,String> buildTagMap() {
		final HashMap<String,String> map=new HashMap<String,String>(132,1.0F); // 99 tags total
		for (String tagName : ALL) map.put(tagName,tagName);
		map.put(StartTagTypeMarkupDeclaration.ELEMENT,StartTagTypeMarkupDeclaration.ELEMENT);
		map.put(StartTagTypeMarkupDeclaration.ATTLIST,StartTagTypeMarkupDeclaration.ATTLIST);
		map.put(StartTagTypeMarkupDeclaration.ENTITY,StartTagTypeMarkupDeclaration.ENTITY);
		map.put(StartTagTypeMarkupDeclaration.NOTATION,StartTagTypeMarkupDeclaration.NOTATION);
		map.put(StartTagTypeMicrosoftDownlevelRevealedConditionalComment.IF,StartTagTypeMicrosoftDownlevelRevealedConditionalComment.IF);
		map.put(StartTagTypeMicrosoftDownlevelRevealedConditionalComment.ENDIF,StartTagTypeMicrosoftDownlevelRevealedConditionalComment.ENDIF);
		return map;
	}
}
