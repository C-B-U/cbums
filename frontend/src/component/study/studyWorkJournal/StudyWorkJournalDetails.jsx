import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournal/study_workJournal.module.css";

class StudyWorkJournalDetails extends PureComponent {
  render() {
    return (
      <React.Fragment>
        <div className={style["main__details-wrap"]}>
          <div className={style["main__details"]}>
            {/*--스터디 상세설명--*/}
            <div className={style["main__details-members"]}>
              {/*!--팀원--*/}
              <span>
                <b>팀장</b>
                (6기 최부엉)
              </span> /
              <span>
                <b> 팀원</b>
                (5기 000, 6기 000, 7기 000)
              </span>
            </div>
            <div className={style["main__details-tags"]}>
              {/*--설정한 태그--*/}
              <ul>
                <li className={style["main__details-tag-item"]}>스터디</li>
                <li className={style["main__details-tag-item"]}>JAVA</li>
              </ul>
            </div>
          </div>
        </div>
      </React.Fragment>
    );
  }
}

export default StudyWorkJournalDetails;
