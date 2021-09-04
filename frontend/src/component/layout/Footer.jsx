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
            <div className="footer-wrap">
              <div className="footer__info">
                <div className="footer__info-wrap">
                  <Information />
                </div>
              </div>
            </div>
          </footer>
        </Default>
        <Mobile>
          <footer>
            <div className="footer-wrap">
              <div className="footer__info">
                <div className="footer__info-wrap">
                  <Information />
                  <div className="footer__info-logo">
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
