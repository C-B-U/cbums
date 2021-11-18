import React, { PureComponent } from 'react';
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";

class RecruitDetailStudyTag extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__studyComplete-tag-wrap"]}>
          {/*!--태그--*/}
          <ul>
            <li className={style["main__studyComplete-tag"]}>web</li>
            <li className={style["main__studyComplete-tag"]}>스터디</li>
          </ul>
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyTag;