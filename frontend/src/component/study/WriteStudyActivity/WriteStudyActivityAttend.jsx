import React, { PureComponent } from "react";
import style from "../../../css/study/study_workJournalWriting/WriteStudyActivity.module.css";
import styled from "styled-components";
const Input = styled.input`
  margin: 0.5em;
`;

class WriteStudyActivityAttend extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      status: "결석",
      backgroundColor: "#ffb9b9",
      value: 0 /* (value) 결석:0, 지각:1, 출석:2 */,
    };
    this.switchValue = this.switchValue.bind(this);
  }
  switchValue = () => {
    if (this.state.status === "결석") {
      this.setState({
        status: "지각",
        backgroundColor: "#fff6b1",
        value: 1,
      });
    } else if (this.state.status === "지각") {
      this.setState({
        status: "출석",
        backgroundColor: "#b8ff8f",
        value: 2,
      });
    } else {
      this.setState({
        status: "결석",
        backgroundColor: "#ffb9b9",
        value: 0,
      });
    }
  };
  render() {
    return (
      <React.Fragment>
        <div
          className={style["main__writing-table-button"]}
          style={{ backgroundColor: this.state.backgroundColor }}
          onClick={this.switchValue}
        >
          {this.state.status}
        </div>
        <Input
          className={style["main__writing-table-attend"]}
          type="hidden"
          name={this.props.name}
          value={this.state.value}
        />
      </React.Fragment>
    );
  }
}

export default WriteStudyActivityAttend;
