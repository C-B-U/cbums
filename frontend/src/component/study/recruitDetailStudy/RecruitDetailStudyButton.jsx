import React, { PureComponent } from "react";
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";

class RecruitDetailStudyButton extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style.button_wrap}>
          <button
            type="button"
            className={style.list_button}
          >
            목록
          </button>
          <div className={style.writer_button}>
            <button type="button" className={style.modify_button}>
              수정
            </button>
            <button type="button" className={style.delete_button}>
              삭제
            </button>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyButton;
