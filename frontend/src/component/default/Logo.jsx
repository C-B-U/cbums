import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import logo from "../../images/default/logo.png";
import mobileLogo from "../../images/default/mobileLogo.png";
import { Link } from "react-router-dom";

class Logo extends PureComponent {
  render() {
    const { logoHeight } = this.props;
    return (
      <React.Fragment>
        <Default>
          <div className="header-wrap__logo">
            <Link to="/">
              <img
                id="logo_img"
                src={logo}
                alt="씨부엉 상단 로고"
                height={logoHeight}
              />
            </Link>
          </div>
        </Default>
        <Mobile>
          <div className="header-wrap__logo-mobile">
            <Link to="/">
              <img
                src={mobileLogo}
                alt="씨부엉 상단 로고 모바일"
                height={logoHeight}
              />
            </Link>
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}
export default Logo;
