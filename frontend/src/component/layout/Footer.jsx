import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import Logo from "../default/Logo";
import Information from "../default/Information";
import "../../css/footer.css";

class Footer extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <footer>
            <div class="footer-wrap">
              <div class="footer__info">
                <div class="footer__info-wrap">
                  <Information />
                </div>
              </div>
            </div>
          </footer>
        </Default>
        <Mobile>
          <footer>
            <div class="footer-wrap">
              <div class="footer__info">
                <div class="footer__info-wrap">
                  <Information />
                  <div class="footer__info-logo">
                    <Logo logoHeight={32} />
                  </div>
                </div>
              </div>
            </div>
          </footer>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Footer;
/*

                <div class="footer__info">
                   <div class="footer__info-wrap">
                    <div class="footer__info-CBU">
                        
                        
                    </div>
                    <div class="footer__info-staffs">
                       
                        <ul class="footer__info-staffA footer_mobile">
                            <li>회장 김김김</li>
                            <li>010-1010-1010</li>
                            <li >naver@naver.com</li>
                        </ul>
                        <ul class="footer__info-staffB footer_mobile">
                            <li>부회장 박박박</li>
                            <li>010-1010-1010</li>
                            <li>naver@naver.com</li>
                        </ul>
                    </div>
                    
                  </div>

                </div>
                
            </div>
        </footer> */
