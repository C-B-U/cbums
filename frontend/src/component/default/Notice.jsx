import React, { PureComponent } from "react";
import Join1 from "../../images/default/join1.jpg";
import Join2 from "../../images/default/join2.jpg";
import Join3 from "../../images/default/join3.jpg";
import ContainerTitle from "./ContainerTitle";
import { Link } from "react-router-dom";
import Button from "./Button";
import { Default, Mobile } from "../MediaQuery";
import NoticeSlide from "./NoticeSlide";
import style from "../../css/default/default.module.css";

class Notice extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <Default>
          <div className={style.main__join}>
            <ContainerTitle title="Notice" />
            <div className={style["main__join-image"]}>
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
            <ul className={style["main__join-text"]}>
              <li>능동적인 공부</li>
              <li>협력적인 프로젝트</li>
              <li>어쩌고저쩌고</li>
            </ul>
            <div className={style["main__join-button-wrap"]}>
              <Link to="/">
                <Button name="공지사항" className={style['main__join-button']} />
              </Link>
            </div>
          </div>
        </Default>
        <Mobile>
          <div className={style["main__Notice"]}>
            <ContainerTitle title="공지사항" />
            <NoticeSlide />
          </div>
        </Mobile>
      </React.Fragment>
    );
  }
}

export default Notice;
