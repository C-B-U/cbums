import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import logo from "../../images/default/logo.png";
import mobileLogo from "../../images/default/mobileLogo.png";

class Logo extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <div className="header-wrap__logo">
            {/* 이미지 클릭 이벤트 필요 */}
            <img id="logo_img" src={logo} alt="씨부엉 상단 로고" height="36" />
          </div>
        </Default>
        <Mobile>
          <div className="header-wrap__logo-mobile mobile">
            <img src={mobileLogo} alt="씨부엉 상단 로고 모바일" height="36" />
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}
export default Logo;
