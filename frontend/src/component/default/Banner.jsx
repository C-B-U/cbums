import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import { Link } from "react-router-dom";
import DefaultBanner from "../../images/default/header.png";
import MobileBanner from "../../images/default/mobileBanner2.png";
class Banner extends PureComponent {
  render() {
    const imgStyle = {
      width: "100%",
    };
    return (
      <React.Fragment>
        <Default>
          <div className="main__CBU-image">
            <Link to="/">
              <img
                src={DefaultBanner}
                alt="씨부엉 메인 배너"
                style={imgStyle}
              />
            </Link>
          </div>
        </Default>
        <Mobile>
          <div className="main__CBU-image-moible">
            <img
              src={MobileBanner}
              alt="씨부엉 메인 배너 모바일"
              style={imgStyle}
            />
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Banner;
