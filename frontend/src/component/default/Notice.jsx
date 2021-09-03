import React, { PureComponent } from "react";
import Join1 from "../../images/default/join1.jpg";
import Join2 from "../../images/default/join2.jpg";
import Join3 from "../../images/default/join3.jpg";
import ContainerTitle from "./ContainerTitle";
import { Link } from "react-router-dom";
import Button from "./Button";
class Notice extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div class="main__join">
          <ContainerTitle title="Notice" />
          <div class="main__join-image">
            <div>
              <img src={Join1} alt="동아리 소개1" />
            </div>
            <div>
              <img src={Join2} alt="동아리 소개2" />
            </div>
            <div>
              <img src={Join3} alt="동아리 소개3" />
            </div>
          </div>
          <ul class="main__join-text">
            <li>능동적인 공부</li>
            <li>협력적인 프로젝트</li>
            <li>어쩌고저쩌고</li>
          </ul>
          <div class="main__join-button-wrap">
            <Link to="/">
              <Button name="공지사항" />
            </Link>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default Notice;
