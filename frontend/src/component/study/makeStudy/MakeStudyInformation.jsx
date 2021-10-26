import React, { PureComponent } from "react";
import style from "../../../css/study/study_make/study_make.module.css";
class MakeStudyInformation extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <form action="#" method="POST" name="study_info">
          <div className={style["main__studymake-information"]}>
            <div className={style["main__slide-studyname"]}>
              스터디명
              <input
                className={style.studymake_input}
                type="text"
                name="studyname"
                autoComplete="off"
              />
            </div>
            <div className={style["main__slide-duration"]}>
              진행 기간
              <input
                className={style.studymake_input}
                type="date"
                name="duration-first"
              />
              ~
              <input
                className={style.studymake_input}
                type="date"
                name="duration-last"
              />
            </div>
            <div className={style["main__slide-number"]}>
              모집 인원
              <input
                className={style.studymake_input}
                type="text"
                name="number-of-study"
                autoComplete="off"
              />
              명
            </div>
          </div>
        </form>
      </React.Fragment>
    );
  }
}

export default MakeStudyInformation;
