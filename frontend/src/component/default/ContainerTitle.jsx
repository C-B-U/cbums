import React, { PureComponent } from "react";
import { Default, Mobile } from "../MediaQuery";
import { Link } from "react-router-dom";
import { FaChevronRight } from "react-icons/fa";

class ContainerTitle extends PureComponent {
  render() {
    const { title } = this.props;
    const iconStyle = {
      color: "black",
    };
    return (
      <React.Fragment>
        <Default>
          <div class="main__study-info">
            <h2 class="main__study-h2">{title}</h2>
          </div>
        </Default>
        <Mobile>
          <div class="main__study-info">
            <h2 class="main__study-h2">{title}</h2>
            <div class="main__study-detail-mobile">
              <Link to="/">
                <p>자세히 보기</p>
                <FaChevronRight style={iconStyle} />
              </Link>
            </div>
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default ContainerTitle;
