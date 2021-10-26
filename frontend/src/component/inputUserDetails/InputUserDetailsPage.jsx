import React, { PureComponent } from "react";
import style from "../../css/signUp/inputSignUpContent/inputSignUpContent.module.css";
import InputContentList from "./InputContentList";
import InputUserDetailsTitle from "./InputUserDetailsTitle";

class InputUserDetailsPage extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <article className={style.article}>
          <div className={style["article__inner"]}>
            <InputUserDetailsTitle
              title="추가 정보 입력"
              titleClass={style.article__boxTitle}
              imageClass={style.article__boxDeco}
            />
            <InputContentList />
          </div>
        </article>
      </React.Fragment>
    );
  }
}

export default InputUserDetailsPage;
