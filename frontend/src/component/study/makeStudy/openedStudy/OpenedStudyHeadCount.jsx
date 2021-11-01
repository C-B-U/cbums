import React, { PureComponent } from 'react';
import style from "../../../../css/study/study_make_complete/study_make_complete.module.css";

class OpenedStudyHeadCount extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__studyComplete-number-wrap"]}>
              <div className={style["main__studyComplete-number"]}>인원 수: 0/4 </div>
              {/*--인원수--*/}
              <form>
                <button className={style["main__studyComplete-number-apply"]}> 신청</button>
                {/*!--신청 버튼--*/}
              </form>
            </div>
          </React.Fragment>
        );
    }
}

export default OpenedStudyHeadCount;