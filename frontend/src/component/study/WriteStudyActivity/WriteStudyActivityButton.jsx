import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";

class WriteStudyActivityButton extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__writing-button-wrap"]}>
              <button
                type="button"
                className={style["main__writing-button-cancel"]}
              >
                취소
              </button>
              <button
                type="button"
                className={style["main__writing-button-write"]}
              >
                등록
              </button>
            </div>
          </React.Fragment>
        );
    }
}

export default WriteStudyActivityButton;
