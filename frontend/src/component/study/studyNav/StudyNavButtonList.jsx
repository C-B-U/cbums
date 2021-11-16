import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournal/study_work.module.css";
import { FaCalendarCheck } from "react-icons/fa";
import { FaListAlt } from "react-icons/fa";
import { Link } from "react-router-dom";

class StudyNavButtonList extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <div className={style["main__studyPlan"]}>
              <div className={style["main__studyPlan-button"]}>
                <Link to="/">
                  <FaCalendarCheck /> 계획서 작성
                </Link>
              </div>
              <div className={style["main__studyPlan-work-button"]}>
                <Link to="/">
                  <FaListAlt /> 활동일지
                </Link>
              </div>
            </div>
          </React.Fragment>
        );
    }
}

export default StudyNavButtonList;
