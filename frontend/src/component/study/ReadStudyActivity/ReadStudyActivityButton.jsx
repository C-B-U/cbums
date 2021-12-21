import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalReading/study_workJournalReading.module.css";

class ReadStudyActivityButton extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__reading-button-wrap"]}>
              <button type="button" className={style["main__reading-button-modify"]}>
                수정
              </button>
              <button type="button" className={style["main__reading-button-delete"]}>
                삭제
              </button>
            </div>
          </React.Fragment>
        );
    }
}

export default ReadStudyActivityButton;
