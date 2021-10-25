import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import Logo from "./Logo";
import FooterContent from "./FooterContent";
import style from "../../css/footer.module.css";

class Footer extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <footer>
            <div className={style["footer-wrap"]}>
              <div className={style.footer__info}>
                <div className={style["footer__info-wrap"]}>
                  <FooterContent />
                </div>
              </div>
            </div>
          </footer>
        </Default>
        <Mobile>
          <footer>
            <div className={style["footer-wrap"]}>
              <div className={style.footer__info}>
                <div className={style["footer__info-wrap"]}>
                  <FooterContent />
                  <div className={style["footer__info-logo"]}>
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
