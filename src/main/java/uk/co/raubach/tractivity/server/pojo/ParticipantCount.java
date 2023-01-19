package uk.co.raubach.tractivity.server.pojo;

import uk.co.raubach.tractivity.server.database.codegen.enums.ParticipantsGender;

public class ParticipantCount
{
	private Integer participantId;
	private String participantName;
	private ParticipantsGender participantGender;
	private Integer count;

	public Integer getParticipantId()
	{
		return participantId;
	}

	public ParticipantCount setParticipantId(Integer participantId)
	{
		this.participantId = participantId;
		return this;
	}

	public String getParticipantName()
	{
		return participantName;
	}

	public ParticipantCount setParticipantName(String participantName)
	{
		this.participantName = participantName;
		return this;
	}

	public ParticipantsGender getParticipantGender()
	{
		return participantGender;
	}

	public ParticipantCount setParticipantGender(ParticipantsGender participantGender)
	{
		this.participantGender = participantGender;
		return this;
	}

	public Integer getCount()
	{
		return count;
	}

	public ParticipantCount setCount(Integer count)
	{
		this.count = count;
		return this;
	}
}
