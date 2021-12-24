import React, { PureComponent } from "react";
import style from "../../../css/study/study_make_complete/study_make_complete.module.css";

class RecruitDetailStudyTextArea extends PureComponent {
    render() {
        return (
          <React.Fragment>
            <textarea
              className={style.plan_textarea}
              name={this.props.name}
              style={{
                resize: "none",
                width: this.props.width,
                height: this.props.height,
              }}
             
              required
              wrap="hard"
            />
          </React.Fragment>
        );
    }
}

export default RecruitDetailStudyTextArea;
