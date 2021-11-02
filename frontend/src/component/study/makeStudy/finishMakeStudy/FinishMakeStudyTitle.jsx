import React, { PureComponent } from 'react';
import style from "../../../../css/study/study_make_complete/study_make_complete.module.css";

class FinishMakeStudyTitle extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__studyComplete-title-wrap"]}>
          <div className={style["main__studyComplete-title"]}>
            <h2>CBUMS</h2>
          </div>
          {/*--스터디 이름-->*/}
          <div className={style["main__studyComplete-status-wrap"]}>
            <div className={style["main__studyComplete-status"]}>모집중</div>
            {/*--스터디 모집 상태-->*/}
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default FinishMakeStudyTitle;