import React, { PureComponent } from 'react';
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";

class RecruitDetailStudyContents extends PureComponent {
  render() {
    const duration = "2021/1/1 ~";
    const furtherExplanation =
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo nihil sint explicabo molestias perspiciatis. Molestiae, illum natus? Id incidunt est temporibus, ab ducimus quis, porro odio autem quo, quidem tempora. Lorem ipsum dolor sit amet consectetur, adipisicing elit. Eum mollitia neque error corrupti sunt, at aspernatur consequatur iure libero suscipit in atque praesentium illo possimus saepe ut similique laborum necessitatibus?";
    return (
      <React.Fragment>
        <div className={style["main__studyComplete-details-wrap"]}>
          <div className={style.partition} />
          <div className={style["main__studyComplete-details-duration"]}>
            <strong>진행기간</strong>: {duration}
          </div>
          {/*!--진행 기간--*/}
          <div className={style["main__studyComplete-details-explanation"]}>
            <strong>부가설명</strong>: {furtherExplanation}
          </div>
          {/*!--세부 내용--*/}
        </div>
      </React.Fragment>
    );
  }
}

export default RecruitDetailStudyContents;