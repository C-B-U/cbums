import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
class Information extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <div class="footer__info-CBU">
            <div class="footer_mobile">씨부엉</div>
            <div class="footer_mobile">http://www.abcdefgh.ac.kr</div>
            <div class="footer_info-mobile">한국산업기술대학교</div>
            <div class="footer_info-mobile">프로그래밍 동아리</div>
          </div>
          <div class="footer__info-staffs">
            <ul class="footer__info-staffA footer_mobile">
              <li>회장 김김김</li>
              <li>010-1010-1010</li>
              <li>naver@naver.com</li>
            </ul>
            <ul class="footer__info-staffB footer_mobile">
              <li>부회장 박박박</li>
              <li>010-1010-1010</li>
              <li>naver@naver.com</li>
            </ul>
          </div>
        </Default>
        <Mobile>
          <div class="footer__info-CBU">
            <div class="footer_info-mobile">한국산업기술대학교</div>
            <div class="footer_info-mobile">프로그래밍 동아리</div>
          </div>
          <div class="footer__info-staffs">
            <div class="footer__info-staffs-mobile">
              <div class="footer_info-mobile">회장 김김김</div>
              <div class="footer_info-mobile">010-1010-1010</div>
            </div>
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Information;
