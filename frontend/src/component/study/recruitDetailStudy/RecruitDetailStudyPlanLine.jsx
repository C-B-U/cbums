import React, { PureComponent } from "react";
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";
import RecruitDetailStudyTextArea from "./RecruitDetailStudyTextArea";

class RecruitDetailStudyPlanLine extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <tr>
              <th>{this.props.times}</th>
              <td>
                <RecruitDetailStudyTextArea
                  name="study-content"
                  width="85%"
                  height="60px"
                />
              </td>
              <td>
                <RecruitDetailStudyTextArea
                  name="study-method"
                  width="85%"
                  height="60px"
                />
              </td>
              <td>
                <input className={style.date} type="date" />
              </td>
            </tr>
          </React.Fragment>
        );
    }
}

export default RecruitDetailStudyPlanLine;
