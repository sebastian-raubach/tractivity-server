package uk.co.raubach.tractivity.server.pojo;

import uk.co.raubach.tractivity.server.database.codegen.enums.ParticipantsGender;

import java.sql.*;

public class SimpleParticipant
{
	private Integer            participantId;
	private String             participantName;
	private Timestamp          participantDob;
	private ParticipantsGender participantGender;

	public Integer getParticipantId()
	{
		return participantId;
	}

	public SimpleParticipant setParticipantId(Integer participantId)
	{
		this.participantId = participantId;
		return this;
	}

	public String getParticipantName()
	{
		return participantName;
	}

	public SimpleParticipant setParticipantName(String participantName)
	{
		this.participantName = participantName;
		return this;
	}

	public Timestamp getParticipantDob()
	{
		return participantDob;
	}

	public SimpleParticipant setParticipantDob(Timestamp participantDob)
	{
		this.participantDob = participantDob;
		return this;
	}

	public ParticipantsGender getParticipantGender()
	{
		return participantGender;
	}

	public SimpleParticipant setParticipantGender(ParticipantsGender participantGender)
	{
		this.participantGender = participantGender;
		return this;
	}
}
