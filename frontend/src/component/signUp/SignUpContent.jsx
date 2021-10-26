import React, { PureComponent } from 'react';
import SignUpButtonList from "../signUp/SignUpButtonList";
import style from "../../css/login/login.module.css";

class SignUpContent extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.article__boxList}>
          <SignUpButtonList />
          <div
            className={`${style.boxList__schoolName} ${style.boxList__schoolName1}`}
          >
            한국산업기술대학교
          </div>
          <div className={style.boxList__schoolName}>
            프로그래밍 동아리 씨부엉
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default SignUpContent;